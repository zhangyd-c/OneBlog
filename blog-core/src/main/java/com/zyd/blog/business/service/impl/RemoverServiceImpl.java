package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.entity.Tags;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.ArticleStatusEnum;
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.business.service.BizArticleTagsService;
import com.zyd.blog.business.service.BizTagsService;
import com.zyd.blog.business.service.RemoverService;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.business.util.ImageDownloadUtil;
import com.zyd.blog.framework.exception.ZhydException;
import com.zyd.blog.spider.model.Article;
import com.zyd.blog.spider.model.BaseModel;
import com.zyd.blog.spider.processor.ArticleSpiderProcessor;
import com.zyd.blog.spider.processor.BaseSpider;
import com.zyd.blog.spider.util.WriterUtil;
import com.zyd.blog.spider.webmagic.ZhydSpider;
import com.zyd.blog.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import us.codecraft.webmagic.Spider;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/8/21 15:38
 * @since 1.8
 */
@Service
public class RemoverServiceImpl implements RemoverService {

    private static final Pattern PATTERN = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");

    @Autowired
    private BizArticleService articleService;
    @Autowired
    private BizTagsService tagsService;
    @Autowired
    private BizArticleTagsService articleTagsService;
    @Autowired
    private SysConfigService sysConfigService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void run(Long typeId, @Validated BaseModel model, PrintWriter writer) {
        WriterUtil writerUtil = new WriterUtil(writer);
        long start = System.currentTimeMillis();
        if (null == typeId) {
            writerUtil.print("校验不通过！请选择文章分类......", String.format("共耗时 %s ms.", (System.currentTimeMillis() - start))).shutdown();
            return;
        }
        User user = SessionUtil.getUser();
        BaseSpider<Article> spider = new ArticleSpiderProcessor(model, writerUtil, user.getId());
        CopyOnWriteArrayList<Article> list = spider.run();
        if (CollectionUtils.isEmpty(list)) {
            writerUtil.print(String.format("未抓取到任何内容，请检查目标网站<a href=\"%s\" target=\"_blank\">%s</a>是否可访问！共耗时 %s ms.", model.getEntryUrls()[0], model.getEntryUrls()[0],(System.currentTimeMillis() - start))).shutdown();
            return;
        }
        writerUtil.print("=================================== Next ↓↓↓===================================");
        writerUtil.print("数据抓取完成，此次共抓取到" + list.size() + "条数据。准备持久化到数据库中");

        // 获取数据库中的标签列表
//        List<String> oldTags = tagsService.listAll().stream().map(Tags::getName).collect(toList());
//        List<Tags> tags = tagsService.listAll();
        Map<String, Long> map = tagsService.listAll().stream().collect(Collectors.toMap(tag -> tag.getName().toUpperCase(), Tags::getId));

        // 添加文章到数据库
        com.zyd.blog.business.entity.Article article = null;
        // 处理标签，并获取最终待添加的标签id
        List<Long> tagIds = null;
        Tags newTag = null;
        String qiniuBasePath = sysConfigService.get().getQiuniuBasePath();
        for (Article spiderArticle : list) {
            writerUtil.print("------------------------------------------------------------------------------------");
            writerUtil.print(String.format("准备持久化 - %s", spiderArticle.getTitle()));
            article = new com.zyd.blog.business.entity.Article();
            article.setContent(model.isConvertImg() ? parseImgForHtml(spiderArticle.getSource(), spiderArticle.getContent(), qiniuBasePath, writerUtil) : spiderArticle.getContent());
            article.setTitle(spiderArticle.getTitle());
            article.setTypeId(typeId);
            article.setUserId(user.getId());
            article.setComment(true);
            article.setOriginal(true);
            // 默认是草稿
            article.setStatus(ArticleStatusEnum.UNPUBLISHED.getCode());
            article.setIsMarkdown(false);
            article.setDescription(spiderArticle.getDescription());
            article.setKeywords(spiderArticle.getKeywords());
            article = articleService.insert(article);
            writerUtil.print(String.format("持久化成功 --> <a href=\"%s\" target=\"_blank\">%s</a>", spiderArticle.getSource(), article.getTitle()));

            tagIds = new ArrayList<>();
            for (String tag : spiderArticle.getTags()) {
                if (map.containsKey(tag.toUpperCase())) {
                    tagIds.add(map.get(tag.toUpperCase()));
                    continue;
                }
                newTag = new Tags();
                newTag.setName(tag);
                newTag.setDescription(tag);
                newTag = tagsService.insert(newTag);
                // 防止重复添加，将新添加的标签信息保存到临时map中
                map.put(newTag.getName().toUpperCase(), newTag.getId());
                tagIds.add(newTag.getId());
            }

            // 添加文章-标签关联信息
            articleTagsService.insertList(tagIds.toArray(new Long[0]), article.getId());
            writerUtil.print(String.format("已同步<a href=\"%s\" target=\"_blank\">%s</a>的标签！此次共同步%s个标签", spiderArticle.getSource(), article.getTitle(), tagIds.size()));
        }
        writerUtil.print("=================================== Next ↓↓↓===================================");
        writerUtil.print(String.format("程序执行完毕！共耗时 %s ms.", (System.currentTimeMillis() - start))).shutdown();
    }

    @Override
    public void stop() {
        ZhydSpider spider = ZhydSpider.SPIDER_BUCKET.get(SessionUtil.getUser().getId());
        if (null != spider) {
            Spider.Status status = spider.getStatus();
            if (status.equals(Spider.Status.Running)) {
                spider.stop();
            } else if (status.equals(Spider.Status.Init)) {
                throw new ZhydException("爬虫正在初始化！");
            } else {
                throw new ZhydException("当前没有正在运行的爬虫！");
            }
        } else {
            throw new ZhydException("当前没有正在运行的爬虫！");
        }
    }

    /**
     * 解析Html中的img标签，将图片转存到七牛云
     *
     * @param referer       为了预防某些网站做了权限验证，不加referer可能会403
     * @param html          待解析的html
     * @param qiniuBasePath 七牛的根路径，在config表中配置
     * @param writerUtil    打印输出的工具类
     */
    private String parseImgForHtml(String referer, String html, String qiniuBasePath, WriterUtil writerUtil) {
        if (StringUtils.isEmpty(html)) {
            return null;
        }
        Matcher m = PATTERN.matcher(html);
        Set<String> imgUrlSet = new HashSet<>();
        while (m.find()) {
            String imgUrl = m.group(1);
            imgUrlSet.add(imgUrl);
        }
        if (!CollectionUtils.isEmpty(imgUrlSet)) {
            writerUtil.print(String.format("检测到存在%s张外链图片，开始转存图片到七牛云...", imgUrlSet.size()));
            for (String imgUrl : imgUrlSet) {
                String qiniuImgPath = ImageDownloadUtil.convertToQiniu(imgUrl, referer);
                if (StringUtils.isEmpty(qiniuImgPath)) {
                    writerUtil.print(" * 图片转存失败，请确保七牛云以配置完毕！请查看控制台详细错误信息...");
                    continue;
                }
                html = html.replace(imgUrl, qiniuBasePath + qiniuImgPath);
                writerUtil.print(String.format("<a href=\"%s\" target=\"_blank\">原图片</a> convert to <a href=\"%s%s\" target=\"_blank\">七牛云</a>...", imgUrl, qiniuBasePath, qiniuImgPath));
            }
        }
        return html;
    }
}
