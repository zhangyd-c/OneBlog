package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.entity.Tags;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.ArticleStatusEnum;
import com.zyd.blog.business.service.*;
import com.zyd.blog.business.util.ImageDownloadUtil;
import com.zyd.blog.framework.exception.ZhydException;
import com.zyd.blog.spider.model.BaseModel;
import com.zyd.blog.spider.model.ModelParser;
import com.zyd.blog.spider.model.VirtualArticle;
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
import java.util.*;
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
        this.crawl(typeId, model, writer, true);
    }

    @Override
    public void stop() {
        ZhydSpider spider = ZhydSpider.SPIDER_BUCKET.get(SessionUtil.getUser().getId());
        if (null != spider) {
            Spider.Status status = spider.getStatus();
            if (status.equals(Spider.Status.Running)) {
                spider.stop();
            } else if (status.equals(Spider.Status.Init)) {
                throw new ZhydException("[ crawl ] 爬虫正在初始化！");
            } else {
                throw new ZhydException("[ crawl ] 当前没有正在运行的爬虫！");
            }
        } else {
            throw new ZhydException("[ crawl ] 当前没有正在运行的爬虫！");
        }
    }

    @Override
    public void crawlSingle(Long typeId, String[] urls, PrintWriter writer) {
        WriterUtil writerUtil = new WriterUtil(writer);
        String spiderConfig = sysConfigService.getSpiderConfig();
        for (int i = 0; i < urls.length; i++) {
            BaseModel model = ModelParser.INSTANCE.parseByUrl(urls[i], spiderConfig);
            if (null == model) {
                writerUtil.print("[ crawl ] 暂不支持抓取该平台的文章：" + urls[i]).shutdown();
                continue;
            }
            model.setSingle(true);
            this.crawl(typeId, model, writer, i == urls.length - 1);
        }
    }

    /**
     * 抓取文章的入口
     *
     * @param typeId       文章类型id
     * @param model        model
     * @param writer       writer
     * @param autoShutdown 自动关闭，如果为true，则发生异常或者抓取完成后会自动关闭输出流
     */
    private void crawl(Long typeId, BaseModel model, PrintWriter writer, boolean autoShutdown) {
        WriterUtil writerUtil = new WriterUtil(writer);
        long start = System.currentTimeMillis();
        if (null == typeId) {
            writerUtil.print("[ crawl ] 校验不通过！请选择文章分类......", String.format("共耗时 %s ms.", (System.currentTimeMillis() - start)));
            if (autoShutdown) {
                writerUtil.shutdown();
            }
            return;
        }
        User user = SessionUtil.getUser();
        BaseSpider<VirtualArticle> spider = new ArticleSpiderProcessor(model, writerUtil, user.getId());
        CopyOnWriteArrayList<VirtualArticle> list = spider.run();
        if (CollectionUtils.isEmpty(list)) {
            writerUtil.print(String.format("[ crawl ] 未抓取到任何内容，请确保连接[<a href=\"%s\" target=\"_blank\">%s</a>]是否正确并能正常访问！共耗时 %s ms.", model.getEntryUrls()[0], model.getEntryUrls()[0], (System.currentTimeMillis() - start))).shutdown();
            return;
        }
        writerUtil.print("[ crawl ] Congratulation ! 此次共整理到" + list.size() + "篇文章");

        // 获取数据库中的标签列表
        Map<String, Long> originalTags = tagsService.listAll().stream().collect(Collectors.toMap(tag -> tag.getName().toUpperCase(), Tags::getId));

        // 添加文章到数据库
        Article article = null;
        String qiniuBasePath = sysConfigService.getBaseConfig().getQiniuBasePath();
        for (VirtualArticle spiderVirtualArticle : list) {
            article = this.saveArticle(typeId, model.isConvertImg(), writerUtil, user, qiniuBasePath, spiderVirtualArticle);

            this.saveTags(writerUtil, originalTags, article, spiderVirtualArticle);
        }
        writerUtil.print(String.format("[ crawl ] 搬家完成！耗时 %s ms.", (System.currentTimeMillis() - start)));
        if (autoShutdown) {
            writerUtil.shutdown();
        }
    }

    /**
     * 保存文章
     *
     * @param typeId               文章类型
     * @param isConvertImg         是否转存文章图片
     * @param writerUtil           writer Util
     * @param user                 当前操作的用户
     * @param qiniuBasePath        七牛 bucket路径
     * @param spiderVirtualArticle 爬虫抓取完成的虚拟文章
     */
    private Article saveArticle(Long typeId, boolean isConvertImg, WriterUtil writerUtil, User user, String qiniuBasePath, VirtualArticle spiderVirtualArticle) {
        Article article = new Article();
        article.setContent(isConvertImg ? parseImgForHtml(spiderVirtualArticle.getSource(), spiderVirtualArticle.getContent(), qiniuBasePath, writerUtil) : spiderVirtualArticle.getContent());
        article.setTitle(spiderVirtualArticle.getTitle());
        article.setTypeId(typeId);
        article.setUserId(user.getId());
        article.setComment(true);
        article.setOriginal(true);
        // 默认是草稿
        article.setStatus(ArticleStatusEnum.UNPUBLISHED.getCode());
        article.setIsMarkdown(false);
        article.setDescription(spiderVirtualArticle.getDescription());
        article.setKeywords(spiderVirtualArticle.getKeywords());
        article = articleService.insert(article);
        writerUtil.print(String.format("[ save ] Succeed! <a href=\"%s\" target=\"_blank\">%s</a>", spiderVirtualArticle.getSource(), article.getTitle()));
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
    private void saveTags(WriterUtil writerUtil, Map<String, Long> originalTags, Article article, VirtualArticle spiderVirtualArticle) {
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
        writerUtil.print(String.format("[ sync tags ] Succeed! <a href=\"%s\" target=\"_blank\">%s</a>", spiderVirtualArticle.getSource(), article.getTitle(), tagIds.size()));
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
            writerUtil.print(String.format("[ crawl ] 检测到存在%s张图片，即将转存图片到七牛云...", imgUrlSet.size()));
            for (String imgUrl : imgUrlSet) {
                String qiniuImgPath = ImageDownloadUtil.convertToQiniu(imgUrl, referer);
                if (StringUtils.isEmpty(qiniuImgPath)) {
                    writerUtil.print("[ crawl ] 图片转存失败，请确保七牛云以配置完毕！请查看控制台详细错误信息...");
                    continue;
                }
                html = html.replace(imgUrl, qiniuBasePath + qiniuImgPath);
                writerUtil.print(String.format("[ crawl ] <a href=\"%s\" target=\"_blank\">原图片</a> convert to <a href=\"%s%s\" target=\"_blank\">七牛云</a>...", imgUrl, qiniuBasePath, qiniuImgPath));
            }
        }
        return html;
    }
}
