package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.entity.Tags;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.ArticleStatusEnum;
import com.zyd.blog.business.service.*;
import com.zyd.blog.business.util.ImageDownloadUtil;
import com.zyd.blog.spider.model.Article;
import com.zyd.blog.spider.model.BaseModel;
import com.zyd.blog.spider.processor.ArticleSpiderProcessor;
import com.zyd.blog.spider.processor.BaseSpider;
import com.zyd.blog.spider.util.WriterUtil;
import com.zyd.blog.util.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.PrintWriter;
import java.util.*;
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
@Slf4j
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
    public void run(Long typeId, BaseModel model, PrintWriter writer) {
        long start = System.currentTimeMillis();
        if (null == typeId) {
            WriterUtil.writer2Html(writer, "校验不通过！请选择文章分类......", String.format("共耗时 %s ms.", (System.currentTimeMillis() - start)));
            WriterUtil.shutdown(writer);
            return;
        }
        BaseSpider<Article> spider = new ArticleSpiderProcessor(model, writer);
        List<Article> list = spider.run();
        if (CollectionUtils.isEmpty(list)) {
            WriterUtil.writer2Html(writer, String.format("共耗时 %s ms.", (System.currentTimeMillis() - start)));
            WriterUtil.shutdown(writer);
            return;
        }
        WriterUtil.writer2Html(writer, "抓取完成，共抓取到【" + list.size() + "】条数据...");
        WriterUtil.writer2Html(writer, "准备保存到数据库中...");

        // 获取数据库中的标签列表
//        List<String> oldTags = tagsService.listAll().stream().map(Tags::getName).collect(toList());
//        List<Tags> tags = tagsService.listAll();
        Map<String, Long> map = tagsService.listAll().stream().collect(Collectors.toMap(tag -> tag.getName().toUpperCase(), Tags::getId));

        // 添加文章到数据库
        com.zyd.blog.business.entity.Article article = null;
        // 处理标签，并获取最终待添加的标签id
        List<Long> tagIds = null;
        Tags newTag = null;
        User user = SessionUtil.getUser();
        String qiniuBasePath = sysConfigService.get().getQiuniuBasePath();
        for (Article spiderArticle : list) {
            article = new com.zyd.blog.business.entity.Article();
            article.setContent(model.isConvertImg() ? parseImgForHtml(spiderArticle.getContent(), qiniuBasePath, writer) : spiderArticle.getContent());
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
            WriterUtil.writer2Html(writer, String.format("  > 成功添加【%s】", article.getTitle()), "  > 开始同步文章标签...");

            // 获取代添加的标签集合
//            List<String> reduce = spiderArticle.getTags().stream().filter(item -> !oldTags.contains(item)).collect(toList());

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
            WriterUtil.writer2Html(writer, "  > 文章标签同步完成...");
        }
        WriterUtil.writer2Html(writer, "全部跑完了~！！！...", String.format("共耗时 %s ms.", (System.currentTimeMillis() - start)));
        WriterUtil.shutdown(writer);
    }

    private String parseImgForHtml(String html, String qiniuBasePath, PrintWriter writer) {
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
            WriterUtil.writer2Html(writer, "  > 开始转存图片到七牛云...");
            for (String imgUrl : imgUrlSet) {
                String qiniuImgPath = ImageDownloadUtil.convertToQiniu(imgUrl);
                if (StringUtils.isEmpty(qiniuImgPath)) {
                    WriterUtil.writer2Html(writer, "  >> 图片转存失败，请确保七牛云以配置完毕！请查看控制台详细错误信息...");
                    continue;
                }
                html = html.replaceAll(imgUrl, qiniuBasePath + qiniuImgPath);
                WriterUtil.writer2Html(writer, String.format("  >> <a href=\"%s\" target=\"_blank\">原图片</a> convert to <a href=\"%s\" target=\"_blank\">七牛云</a>...", imgUrl, qiniuImgPath));
            }
        }
        return html;
    }
}
