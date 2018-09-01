package com.zyd.blog.spider.processor;

import com.zyd.blog.spider.model.Article;
import com.zyd.blog.spider.model.BaseModel;
import com.zyd.blog.spider.util.WriterUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Spider;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/7/23 10:38
 * @since 1.0
 */
@Slf4j
public class ArticleSpiderProcessor extends BaseProcessor implements ZydSpider<Article> {

    private BaseModel model;
    private PrintWriter writer;
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private ArticleSpiderProcessor() {
    }

    public ArticleSpiderProcessor(BaseModel model, PrintWriter writer) {
        super(model);
        this.model = model;
        this.writer = writer;
    }

    public ArticleSpiderProcessor(BaseModel model) {
        super(model);
        this.model = model;
    }

    /**
     * 运行爬虫并返回结果
     *
     * @return
     */
    @Override
    public List<Article> run() {
        List<String> errors = validateModel(model);
        if (CollectionUtils.isNotEmpty(errors)) {
//            log.error("校验不通过！请依据下方提示，检查输入参数是否正确......");
            WriterUtil.writer2Html(writer, "校验不通过！请依据下方提示，检查输入参数是否正确......");
            for (String error : errors) {
//                log.error(">> {}", error);
                WriterUtil.writer2Html(writer, ">> " + error);
            }
            return null;
        }

        List<Article> articles = new LinkedList<>();

//        log.info(">>  共 {} 页数据...", model.getTotalPage());
        WriterUtil.writer2Html(writer, ">> 爬虫初始化完成，共需抓取 " + model.getTotalPage() + " 页数据...");

        Spider spider = Spider.create(new ArticleSpiderProcessor())
                .addUrl(model.getEntryUrls())
                .addPipeline((resultItems, task) -> {
                    Map<String, Object> map = resultItems.getAll();
                    String title = String.valueOf(map.get("title"));
                    if (StringUtils.isEmpty(title) || "null".equals(title)) {
                        return;
                    }
                    String content = String.valueOf(map.get("content"));
                    String source = String.valueOf(map.get("source"));
                    String releaseDate = String.valueOf(map.get("releaseDate"));
                    String author = String.valueOf(map.get("author"));
                    String description = String.valueOf(map.get("description"));
                    description = StringUtils.isNotEmpty(description) ? description.replaceAll("\r\n| ", "")
                            : content.length() > 100 ? content.substring(0, 100) : content;
                    String keywords = String.valueOf(map.get("keywords"));
                    keywords = StringUtils.isNotEmpty(keywords) && !"null".equals(keywords) ? keywords.replaceAll(" +|，", ",").replaceAll(",,", ",") : null;
                    List<String> tags = (List<String>) map.get("tags");
                    log.info(String.format(">> 正在抓取 -- %s -- %s -- %s -- %s", source, title, releaseDate, author));
                    WriterUtil.writer2Html(writer, String.format(">> 正在抓取 -- <a href=\"%s\" target=\"_blank\">%s</a> -- %s -- %s", source, title, releaseDate, author));
                    articles.add(new Article(title, content, author, releaseDate, source, description, keywords, tags));
                })
                .thread(model.getThreadCount());
        // 启动爬虫
        spider.run();
        return articles;
    }

    private <T> List<String> validateModel(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }
}
