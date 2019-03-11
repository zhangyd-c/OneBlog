package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.entity.Tags;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.ArticleStatusEnum;
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.business.service.BizArticleTagsService;
import com.zyd.blog.business.service.BizTagsService;
import com.zyd.blog.business.service.RemoverService;
import com.zyd.blog.business.util.ImageDownloadUtil;
import com.zyd.blog.util.SessionUtil;
import me.zhyd.hunter.Hunter;
import me.zhyd.hunter.config.HunterConfig;
import me.zhyd.hunter.entity.ImageLink;
import me.zhyd.hunter.entity.VirtualArticle;
import me.zhyd.hunter.processor.BlogHunterProcessor;
import me.zhyd.hunter.processor.HunterProcessor;
import me.zhyd.hunter.util.HunterPrintWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
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

    @Autowired
    private BizArticleService articleService;
    @Autowired
    private BizTagsService tagsService;
    @Autowired
    private BizArticleTagsService articleTagsService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void run(Long typeId, @Validated HunterConfig config, PrintWriter writer) {
        HunterPrintWriter writerUtil = new HunterPrintWriter(writer);
        long start = System.currentTimeMillis();
        if (null == typeId) {
            writerUtil.print("校验不通过！请选择文章分类......", String.format("共耗时 %s ms.", (System.currentTimeMillis() - start)));
            writerUtil.shutdown();
            return;
        }
        HunterProcessor hunter = new BlogHunterProcessor(config, writerUtil, String.valueOf(SessionUtil.getUser().getId()));
        CopyOnWriteArrayList<VirtualArticle> list = hunter.execute();
        if (CollectionUtils.isEmpty(list)) {
            writerUtil.print(String.format("未抓取到任何内容，请确保连接[<a href=\"%s\" target=\"_blank\">%s</a>]是否正确并能正常访问！共耗时 %s ms.", config.getEntryUrls().get(0), config.getEntryUrls().get(0), (System.currentTimeMillis() - start))).shutdown();
            return;
        }
        writerUtil.print("Congratulation ! 此次共整理到" + list.size() + "篇文章");
        saveArticles(typeId, config, writerUtil, list);

        writerUtil.print(String.format("搬家完成！耗时 %s ms.", (System.currentTimeMillis() - start)));
        writerUtil.shutdown();
    }

    @Override
    public void stop() {
        Hunter spider = Hunter.getHunter(String.valueOf(SessionUtil.getUser().getId()));
        spider.stop();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void crawlSingle(Long typeId, String[] urls, boolean convertImg, PrintWriter writer) {
        HunterPrintWriter writerUtil = new HunterPrintWriter(writer);
        for (String url : urls) {
            HunterProcessor hunter = new BlogHunterProcessor(url, convertImg, writerUtil);
            CopyOnWriteArrayList<VirtualArticle> list = hunter.execute();
            this.saveArticles(typeId, hunter.getConfig(), writerUtil, list);
        }
        writerUtil.shutdown();
    }

    private void saveArticles(Long typeId, HunterConfig config, HunterPrintWriter writerUtil, CopyOnWriteArrayList<VirtualArticle> list) {
        // 获取数据库中的标签列表
        Map<String, Long> originalTags = tagsService.listAll().stream().collect(Collectors.toMap(tag -> tag.getName().toUpperCase(), Tags::getId));

        User user = SessionUtil.getUser();
        // 添加文章到数据库
        Article article = null;
        for (VirtualArticle spiderVirtualArticle : list) {
            article = this.saveArticle(typeId, config.isConvertImg(), writerUtil, user, spiderVirtualArticle);

            this.saveTags(writerUtil, originalTags, article, spiderVirtualArticle);
        }
    }

    private Article saveArticle(Long typeId, boolean isConvertImg, HunterPrintWriter writerUtil, User user, VirtualArticle virtualArticle) {
        Article article = new Article();
        article.setContent(isConvertImg ? parseImgForHtml(virtualArticle, writerUtil) : virtualArticle.getContent());
        article.setTitle(virtualArticle.getTitle());
        article.setCoverImage(CollectionUtils.isEmpty(virtualArticle.getImageLinks()) ? null : new ArrayList<>(virtualArticle.getImageLinks()).get(0).getSrcLink());
        article.setTypeId(typeId);
        article.setUserId(user.getId());
        article.setComment(true);
        article.setOriginal(true);
        // 默认是草稿
        article.setStatus(ArticleStatusEnum.UNPUBLISHED.getCode());
        article.setIsMarkdown(false);
        article.setDescription(virtualArticle.getDescription());
        article.setKeywords(virtualArticle.getKeywords());
        article = articleService.insert(article);
        writerUtil.print(String.format("[ save ] Succeed! <a href=\"%s\" target=\"_blank\">%s</a>", virtualArticle.getSource(), article.getTitle()));
        return article;
    }

    /**
     * 保存文章的标签
     *
     * @param writerUtil           writer
     * @param originalTags         原始的标签列表
     * @param article              以保存的文章
     * @param spiderVirtualArticle 爬虫抓取完成的虚拟文章
     */
    private void saveTags(HunterPrintWriter writerUtil, Map<String, Long> originalTags, Article article, VirtualArticle spiderVirtualArticle) {
        List<Long> tagIds = new ArrayList<>();
        Tags newTag;
        for (String tag : spiderVirtualArticle.getTags()) {
            if (originalTags.containsKey(tag.toUpperCase())) {
                tagIds.add(originalTags.get(tag.toUpperCase()));
                continue;
            }
            newTag = new Tags();
            newTag.setName(tag);
            newTag.setDescription(tag);
            newTag = tagsService.insert(newTag);
            // 防止重复添加，将新添加的标签信息保存到临时map中
            originalTags.put(newTag.getName().toUpperCase(), newTag.getId());
            tagIds.add(newTag.getId());
        }

        // 添加文章-标签关联信息
        articleTagsService.insertList(tagIds.toArray(new Long[0]), article.getId());
        writerUtil.print(String.format("[ sync tags ] Succeed! <a href=\"%s\" target=\"_blank\">%s</a>", spiderVirtualArticle.getSource(), article.getTitle()));
    }

    /**
     * 解析Html中的img标签，将图片转存到七牛云
     *
     * @param spiderVirtualArticle spiderVirtualArticle
     * @param writerUtil           打印输出的工具类
     */
    private String parseImgForHtml(VirtualArticle spiderVirtualArticle, HunterPrintWriter writerUtil) {
        if (null == spiderVirtualArticle) {
            return null;
        }
        String source = spiderVirtualArticle.getSource();
        Set<ImageLink> imageLinks = spiderVirtualArticle.getImageLinks();
        String html = spiderVirtualArticle.getContent();
        if (!CollectionUtils.isEmpty(imageLinks)) {
            writerUtil.print(String.format("检测到存在%s张图片，即将转存图片到云存储服务器中...", imageLinks.size()));
            Iterator<ImageLink> it = imageLinks.iterator();
            while (it.hasNext()) {
                ImageLink imageLink = it.next();
                String resImgPath = null;
                try {
                    resImgPath = ImageDownloadUtil.saveToCloudStorage(imageLink.getSrcLink(), source);
                    if (StringUtils.isEmpty(resImgPath)) {
                        writerUtil.print("图片转存失败，请确保云存储已经配置完毕！请查看控制台详细错误信息...");
                        continue;
                    }
                } catch (Exception e) {
                    writerUtil.print(e.getMessage());
                    it.remove();
                    continue;
                }
                html = html.replace(imageLink.getSrcLink(), resImgPath);
                writerUtil.print(String.format("<a href=\"%s\" target=\"_blank\">原图片</a> 已经转存到 <a href=\"%s\" target=\"_blank\">云存储</a>...", imageLink.getSrcLink(), resImgPath));
            }
        }
        return html;
    }
}
