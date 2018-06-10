/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.annotation.RedisCache;
import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.ArticleStatusEnum;
import com.zyd.blog.business.enums.QiniuUploadType;
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.business.service.BizArticleTagsService;
import com.zyd.blog.business.vo.ArticleConditionVO;
import com.zyd.blog.framework.exception.ZhydArticleException;
import com.zyd.blog.framework.holder.RequestHolder;
import com.zyd.blog.persistence.beans.BizArticle;
import com.zyd.blog.persistence.beans.BizArticleLove;
import com.zyd.blog.persistence.beans.BizArticleTags;
import com.zyd.blog.persistence.beans.BizTags;
import com.zyd.blog.persistence.mapper.BizArticleLookMapper;
import com.zyd.blog.persistence.mapper.BizArticleLoveMapper;
import com.zyd.blog.persistence.mapper.BizArticleMapper;
import com.zyd.blog.persistence.mapper.BizArticleTagsMapper;
import com.zyd.blog.util.FileUtil;
import com.zyd.blog.util.IpUtil;
import com.zyd.blog.util.SessionUtil;
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

/**
 * 文章列表
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Service
public class BizArticleServiceImpl implements BizArticleService {

    @Autowired
    private BizArticleMapper bizArticleMapper;
    @Autowired
    private BizArticleLoveMapper bizArticleLoveMapper;
    @Autowired
    private BizArticleLookMapper bizArticleLookMapper;
    @Autowired
    private BizArticleTagsMapper bizArticleTagsMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BizArticleTagsService articleTagsService;

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
        List<Long> ids = new ArrayList<>();
        for (BizArticle bizArticle : list) {
            ids.add(bizArticle.getId());
        }
        List<BizArticle> listTag = bizArticleMapper.listTagsByArticleId(ids);
        // listTag, 重新组装数据为{id: Article}
        Map<Long, BizArticle> tagMap = new LinkedHashMap<>(listTag.size());
        for (BizArticle bizArticle : listTag) {
            tagMap.put(bizArticle.getId(), bizArticle);
        }

        List<Article> boList = new LinkedList<>();
        for (BizArticle bizArticle : list) {
            BizArticle tagArticle = tagMap.get(bizArticle.getId());
            bizArticle.setTags(tagArticle.getTags());
            boList.add(new Article(bizArticle));
        }
        PageInfo bean = new PageInfo<BizArticle>(list);
        bean.setList(boList);
        return bean;
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
        PageInfo pageInfo = this.findPageBreakByCondition(vo);
        return null == pageInfo ? null : pageInfo.getList();
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
        PageInfo pageInfo = this.findPageBreakByCondition(vo);
        return null == pageInfo ? null : pageInfo.getList();
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
        PageInfo pageInfo = this.findPageBreakByCondition(vo);
        return null == pageInfo ? null : pageInfo.getList();
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
        PageInfo pageInfo = this.findPageBreakByCondition(vo);
        return null == pageInfo ? null : pageInfo.getList();
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
     * 获取素材库
     *
     * @return
     */
    @Override
    public List<String> listMaterial() {
        return bizArticleMapper.listMaterial();
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
            String filePath = FileUtil.uploadToQiniu(file, QiniuUploadType.COVER_IMAGE, true);
            // 保存封面图片
            article.setCoverImage(filePath);
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
        } else {
            article.setRecommended(!article.getRecommended());
        }
        article.setUpdateTime(new Date());
        return bizArticleMapper.updateByPrimaryKeySelective(article) > 0;
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

    /**
     * 保存一个实体，null的属性不会保存，会使用数据库默认值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Article insert(Article entity) {
        Assert.notNull(entity, "Article不可为空！");
        entity.setUpdateTime(new Date());
        entity.setCreateTime(new Date());
        entity.setOriginal(entity.isOriginal());
        entity.setComment(entity.isComment());
        bizArticleMapper.insertSelective(entity.getBizArticle());
        return entity;
    }

    /**
     * 批量插入，支持批量插入的数据库可以使用，例如MySQL,H2等，另外该接口限制实体包含id属性并且必须为自增列
     *
     * @param entities
     */
    @Override
    public void insertList(List<Article> entities) {
        Assert.notNull(entities, "Articles不可为空！");
        List<BizArticle> list = new ArrayList<>();
        for (Article entity : entities) {
            entity.setUpdateTime(new Date());
            entity.setCreateTime(new Date());
            entity.setOriginal(entity.isOriginal());
            entity.setComment(entity.isComment());
            list.add(entity.getBizArticle());
        }
        bizArticleMapper.insertList(list);
    }

    /**
     * 根据主键字段进行删除，方法参数必须包含完整的主键属性
     *
     * @param primaryKey
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByPrimaryKey(Long primaryKey) {
        boolean result = bizArticleMapper.deleteByPrimaryKey(primaryKey) > 0;
        // 删除标签记录
        Example loveExample = new Example(BizArticleTags.class);
        Example.Criteria loveCriteria = loveExample.createCriteria();
        loveCriteria.andEqualTo("articleId", primaryKey);
        bizArticleTagsMapper.deleteByExample(loveExample);
        // 删除查看记录
        Example lookExample = new Example(BizArticleLove.class);
        Example.Criteria lookCriteria = loveExample.createCriteria();
        lookCriteria.andEqualTo("articleId", primaryKey);
        bizArticleLookMapper.deleteByExample(lookExample);
        // 删除赞记录
        Example tagsExample = new Example(BizArticleLove.class);
        Example.Criteria tagsCriteria = tagsExample.createCriteria();
        tagsCriteria.andEqualTo("articleId", primaryKey);
        bizArticleLoveMapper.deleteByExample(tagsExample);
        return result;
    }

    /**
     * 根据主键更新实体全部字段，null值会被更新
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Article entity) {
        Assert.notNull(entity, "Article不可为空！");
        entity.setUpdateTime(new Date());
        entity.setOriginal(entity.isOriginal());
        entity.setComment(entity.isComment());
        return bizArticleMapper.updateByPrimaryKey(entity.getBizArticle()) > 0;
    }

    /**
     * 根据主键更新属性不为null的值
     *
     * @param entity
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSelective(Article entity) {
        Assert.notNull(entity, "Article不可为空！");
        entity.setUpdateTime(new Date());
        entity.setOriginal(entity.isOriginal());
        entity.setComment(entity.isComment());
        return bizArticleMapper.updateByPrimaryKeySelective(entity.getBizArticle()) > 0;
    }

    /**
     * 根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
     *
     * @param primaryKey
     * @return
     */
    @Override
    public Article getByPrimaryKey(Long primaryKey) {
        Assert.notNull(primaryKey, "PrimaryKey不可为空！");
        BizArticle entity = bizArticleMapper.get(primaryKey);
        return null == entity ? null : new Article(entity);
    }

    /**
     * 根据实体中的属性进行查询，只能有一个返回值，有多个结果时抛出异常，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public Article getOneByEntity(Article entity) {
        Assert.notNull(entity, "Article不可为空！");
        BizArticle bo = bizArticleMapper.selectOne(entity.getBizArticle());
        return null == bo ? null : new Article(bo);
    }

    /**
     * 查询全部结果，listByEntity(null)方法能达到同样的效果
     *
     * @return
     */
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

    /**
     * 根据实体中的属性值进行查询，查询条件使用等号
     *
     * @param entity
     * @return
     */
    @Override
    public List<Article> listByEntity(Article entity) {
        Assert.notNull(entity, "Article不可为空！");
        List<BizArticle> entityList = bizArticleMapper.select(entity.getBizArticle());
        if (CollectionUtils.isEmpty(entityList)) {
            return null;
        }
        List<Article> list = new ArrayList<>();
        for (BizArticle po : entityList) {
            list.add(new Article(po));
        }
        return list;
    }
}
