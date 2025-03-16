package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.RedisCache;
import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.entity.BizArticleContentBo;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.ArticleStatusEnum;
import com.zyd.blog.business.enums.CommentStatusEnum;
import com.zyd.blog.business.enums.FileUploadType;
import com.zyd.blog.business.enums.ResponseStatus;
import com.zyd.blog.business.service.BizArticleContentService;
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.business.service.BizArticleTagsService;
import com.zyd.blog.business.vo.ArticleConditionVO;
import com.zyd.blog.file.FileUploader;
import com.zyd.blog.file.entity.VirtualFile;
import com.zyd.blog.framework.exception.ZhydArticleException;
import com.zyd.blog.framework.exception.ZhydException;
import com.zyd.blog.framework.holder.RequestHolder;
import com.zyd.blog.persistence.beans.*;
import com.zyd.blog.persistence.mapper.*;
import com.zyd.blog.plugin.file.GlobalFileUploader;
import com.zyd.blog.util.IpUtil;
import com.zyd.blog.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 文章列表
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Service
public class BizArticleServiceImpl implements BizArticleService {

    @Autowired
    private BizArticleMapper bizArticleMapper;
    @Autowired
    private BizArticleLoveMapper bizArticleLoveMapper;
    @Autowired
    private BizArticleLookV2Mapper bizArticleLookV2Mapper;
    @Autowired
    private BizArticleTagsMapper bizArticleTagsMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BizArticleTagsService articleTagsService;
    @Autowired
    private BizCommentMapper commentMapper;
    @Autowired
    private BizArticleContentService articleContentService;
    @Autowired
    private BizStatisticsMapper bizStatisticsMapper;

    /**
     * 分页查询
     *
     * @param vo
     * @return
     */
    @Override
    public PageInfo<Article> findPageBreakByCondition(ArticleConditionVO vo) {
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizArticle> list = bizArticleMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Long> ids = list.stream().map(BizArticle::getId).collect(Collectors.toList());
        List<BizArticle> listTag = bizArticleMapper.listTagsByArticleId(ids);

        Map<Long, BizArticle> tagMap = listTag.stream().collect(Collectors.toMap(BizArticle::getId, a -> a, (k1, k2) -> k1));
        List<BizStatistics> articleLookCountByArticleIds = Optional.ofNullable(bizStatisticsMapper.listArticleLookCountByArticleIds(ids)).orElse(new ArrayList<>());
        List<BizStatistics> articleCommentCountByArticleIds = Optional.ofNullable(bizStatisticsMapper.listArticleCommentCountByArticleIds(ids)).orElse(new ArrayList<>());
        List<BizStatistics> articleLoveCountByArticleIds = Optional.ofNullable(bizStatisticsMapper.listArticleLoveCountByArticleIds(ids)).orElse(new ArrayList<>());
        Map<String, Integer> lookMap = articleLookCountByArticleIds.stream().collect(Collectors.toMap(BizStatistics::getName, BizStatistics::getValue));
        Map<String, Integer> commentMap = articleCommentCountByArticleIds.stream().collect(Collectors.toMap(BizStatistics::getName, BizStatistics::getValue));
        Map<String, Integer> loveMap = articleLoveCountByArticleIds.stream().collect(Collectors.toMap(BizStatistics::getName, BizStatistics::getValue));
        List<Article> boList = new LinkedList<>();
        Article article = null;
        for (BizArticle bizArticle : list) {
            Long articleId = bizArticle.getId();
            BizArticle tagArticle = tagMap.get(articleId);
            if (null == tagArticle) {
                log.warn("文章[{}] 未绑定标签信息，或者已绑定的标签不存在！", bizArticle.getTitle());
            } else {
                bizArticle.setTags(tagArticle.getTags());
            }

            bizArticle.setLookCount(lookMap.get(String.valueOf(articleId)));
            bizArticle.setCommentCount(commentMap.get(String.valueOf(articleId)));
            bizArticle.setLoveCount(loveMap.get(String.valueOf(articleId)));
            article = new Article(bizArticle);
            article.setPassword(null);
            boList.add(article);
        }
        PageInfo bean = new PageInfo<BizArticle>(list);
        bean.setList(boList);
        return bean;
    }

    private List<Article> convert(ArticleConditionVO vo){
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizArticle> list = bizArticleMapper.findPageBreakByCondition(vo);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<Article> boList = new LinkedList<>();
        Article article = null;
        for (BizArticle bizArticle : list) {
            article = new Article(bizArticle);
            article.setPassword(null);
            boList.add(article);
        }
        return boList;
    }

    /**
     * 站长推荐
     *
     * @param pageSize
     * @return
     */
    @Override
    public List<Article> listRecommended(int pageSize) {
        ArticleConditionVO vo = new ArticleConditionVO();
        vo.setRecommended(true);
        vo.setStatus(ArticleStatusEnum.PUBLISHED.getCode());
        vo.setPageSize(pageSize);
        return this.convert(vo);
    }

    /**
     * 近期文章
     *
     * @param pageSize
     * @return
     */
    @Override
    public List<Article> listRecent(int pageSize) {
        ArticleConditionVO vo = new ArticleConditionVO();
        vo.setPageSize(pageSize);
        vo.setStatus(ArticleStatusEnum.PUBLISHED.getCode());
        return this.convert(vo);
    }

    /**
     * 随机文章
     *
     * @param pageSize
     * @return
     */
    @Override
    public List<Article> listRandom(int pageSize) {
        ArticleConditionVO vo = new ArticleConditionVO();
        vo.setRandom(true);
        vo.setStatus(ArticleStatusEnum.PUBLISHED.getCode());
        vo.setPageSize(pageSize);
        return this.convert(vo);
    }

    /**
     * 根据某篇文章获取与该文章相关的文章<br>
     * 搜索同类型、同标签下的文章
     *
     * @param pageSize
     * @param article
     * @return
     */
    @Override
    public List<Article> listRelatedArticle(int pageSize, Article article) {
        if (null == article) {
            return listRandom(pageSize);
        }
        ArticleConditionVO vo = new ArticleConditionVO();
        vo.setStatus(ArticleStatusEnum.PUBLISHED.getCode());
        List<BizTags> tags = article.getTags();
        if (!CollectionUtils.isEmpty(tags)) {
            List<Long> tagIds = new ArrayList<>();
            for (BizTags tag : tags) {
                tagIds.add(tag.getId());
            }
            vo.setTagIds(tagIds);
        }
        vo.setTypeId(article.getTypeId());
        vo.setPageSize(pageSize);
        return this.convert(vo);
    }

    /**
     * 获取上一篇和下一篇
     *
     * @return
     */
    @Override
    public Map<String, Article> getPrevAndNextArticles(Date insertTime) {
        insertTime = null == insertTime ? new Date() : insertTime;
        List<BizArticle> entityList = bizArticleMapper.getPrevAndNextArticles(insertTime);
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        Map<String, Article> resultMap = new HashMap<>();
        for (BizArticle entity : entityList) {
            if (entity.getCreateTime().getTime() < insertTime.getTime()) {
                resultMap.put("prev", new Article(entity));
            } else {
                resultMap.put("next", new Article(entity));
            }
        }
        return resultMap;
    }

    /**
     * 文章点赞
     *
     * @param id
     */
    @Override
    @RedisCache(flush = true)
    public void doPraise(Long id) {
        String ip = IpUtil.getRealIp(RequestHolder.getRequest());
        String key = ip + "_doPraise_" + id;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            throw new ZhydArticleException("一个小时只能点赞一次哈，感谢支持~~");
        }
        User user = SessionUtil.getUser();
        BizArticleLove love = new BizArticleLove();
        if (null != user) {
            love.setUserId(user.getId());
        }
        love.setArticleId(id);
        love.setUserIp(IpUtil.getRealIp(RequestHolder.getRequest()));
        love.setLoveTime(new Date());
        love.setCreateTime(new Date());
        love.setUpdateTime(new Date());
        bizArticleLoveMapper.insert(love);
        operations.set(key, id, 1, TimeUnit.HOURS);
    }

    /**
     * 是否存在文章
     *
     * @param id
     * @return
     */
    @Override
    public boolean isExist(Long id) {
        Integer count = bizArticleMapper.isExist(id);
        return count != null && count > 0;
    }

    /**
     * 发布文章
     *
     * @param article
     * @param tags
     * @param file
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean publish(Article article, Long[] tags, MultipartFile file) {
        if (null == tags || tags.length <= 0) {
            throw new ZhydArticleException("请至少选择一个标签");
        }
        if (null != file) {
            FileUploader uploader = new GlobalFileUploader();
            VirtualFile virtualFile = uploader.upload(file, FileUploadType.COVER_IMAGE.getPath(), true);
            // 保存封面图片
            article.setCoverImage(virtualFile.getFilePath());
        }
        Long articleId = null;
        if ((articleId = article.getId()) != null) {
            this.updateSelective(article);
        } else {
            article.setUserId(SessionUtil.getUser().getId());
            articleId = this.insert(article).getId();
        }
        if (articleId != null) {
            articleTagsService.removeByArticleId(articleId);
            articleTagsService.insertList(tags, articleId);
        }
        return true;
    }

    /**
     * 修改置顶、推荐
     *
     * @param type
     * @param id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTopOrRecommendedById(String type, Long id) {
        BizArticle article = bizArticleMapper.selectByPrimaryKey(id);
        article.setId(id);
        if ("top".equals(type)) {
            article.setTop(!article.getTop());
        } else if ("recommend".equals(type)) {
            article.setRecommended(!article.getRecommended());
        } else if ("comment".equals(type)) {
            article.setComment(!article.getComment());
        } else {
            throw new ZhydException(ResponseStatus.INVALID_PARAMS.getMessage());
        }
        article.setUpdateTime(new Date());
        return bizArticleMapper.updateByPrimaryKeySelective(article) > 0;
    }

    @Override
    public void batchUpdateStatus(Long[] ids, boolean status) {
        if (ids == null || ids.length <= 0) {
            return;
        }
        bizArticleMapper.batchUpdateStatus(Arrays.asList(ids), status);
    }

    /**
     * 获取热门文章
     *
     * @return
     */
    @Override
    public List<Article> listHotArticle(int pageSize) {
        PageHelper.startPage(1, pageSize);
        List<BizArticle> entityList = bizArticleMapper.listHotArticle();
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Article> list = new ArrayList<>();
        for (BizArticle entity : entityList) {
            list.add(new Article(entity));
        }
        return list;
    }

    @Override
    public List<Article> listOfSitemap(int pageSize) {
        PageHelper.startPage(1, pageSize);
        List<BizArticle> entityList = bizArticleMapper.listOfSitemap();
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Article> list = new ArrayList<>();
        for (BizArticle entity : entityList) {
            list.add(new Article(entity));
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article insert(Article entity) {
        Assert.notNull(entity, "Article不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        entity.setOriginal(entity.isOriginal());
        entity.setComment(entity.isComment());
        bizArticleMapper.insertSelective(entity.getBizArticle());

        BizArticleContentBo bizArticleContentBo = new BizArticleContentBo();
        bizArticleContentBo.setArticleId(entity.getId());
        bizArticleContentBo.setContent(entity.getContent());
        bizArticleContentBo.setContentMd(entity.getContentMd());
        articleContentService.insert(bizArticleContentBo);

        BizArticleLookV2 bizArticleLookV2 = new BizArticleLookV2();
        bizArticleLookV2.setArticleId(entity.getId());
        bizArticleLookV2.setLookCount(0);
        bizArticleLookV2Mapper.insert(bizArticleLookV2);
        return entity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        boolean result = bizArticleMapper.deleteByPrimaryKey(primaryKey) > 0;
        // 删除文章内容
        articleContentService.removeByArticleId(primaryKey);
        // 删除标签记录
        Example tagsExample = new Example(BizArticleTags.class);
        Example.Criteria tagsCriteria = tagsExample.createCriteria();
        tagsCriteria.andEqualTo("articleId", primaryKey);
        bizArticleTagsMapper.deleteByExample(tagsExample);
        // 删除查看记录
        Example lookExample = new Example(BizArticleLookV2.class);
        Example.Criteria lookCriteria = lookExample.createCriteria();
        lookCriteria.andEqualTo("articleId", primaryKey);
        bizArticleLookV2Mapper.deleteByExample(lookExample);
        // 删除赞记录
        Example loveExample = new Example(BizArticleLove.class);
        Example.Criteria loveCriteria = loveExample.createCriteria();
        loveCriteria.andEqualTo("articleId", primaryKey);
        bizArticleLoveMapper.deleteByExample(loveExample);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Article entity) {
        Assert.notNull(entity, "Article不可为空！");
        entity.setUpdateTime(new Date());
        entity.setOriginal(entity.isOriginal());
        entity.setComment(entity.isComment());
        boolean success = bizArticleMapper.updateByPrimaryKeySelective(entity.getBizArticle()) > 0;
        if (!success) {
            return false;
        }
        BizArticleContentBo bizArticleContentBo = new BizArticleContentBo();
        bizArticleContentBo.setArticleId(entity.getId());
        bizArticleContentBo.setContent(entity.getContent());
        bizArticleContentBo.setContentMd(entity.getContentMd());
        articleContentService.updateSelective(bizArticleContentBo);
        return true;
    }

    @Override
    public Article getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizArticle entity = bizArticleMapper.get(primaryKey);
        if (null == entity) {
            return null;
        }
        this.subquery(entity);
        return new Article(entity);
    }

    private void subquery(BizArticle entity) {
        Long primaryKey = entity.getId();
        // 查看的次数
        Example lookExample = new Example(BizArticleLookV2.class);
        Example.Criteria lookCriteria = lookExample.createCriteria();
        lookCriteria.andEqualTo("articleId", primaryKey);
        BizArticleLookV2 content = bizArticleLookV2Mapper.selectOneByExample(lookExample);
        entity.setLookCount(null == content ? 0 : Optional.ofNullable(content.getLookCount()).orElse(0));

        // 评论数
        Example example = new Example(BizComment.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("sid", primaryKey);
        criteria.andEqualTo("status", CommentStatusEnum.APPROVED.toString());
        entity.setCommentCount(commentMapper.selectCountByExample(example));

        // 喜欢的次数
        BizArticleLove love = new BizArticleLove();
        love.setArticleId(primaryKey);
        entity.setLoveCount(bizArticleLoveMapper.selectCount(love));
    }

    @Override
    public List<Article> listAll() {
        List<BizArticle> entityList = bizArticleMapper.selectAll();

        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Article> list = new ArrayList<>();
        for (BizArticle entity : entityList) {
            list.add(new Article(entity));
        }
        return list;
    }
}
