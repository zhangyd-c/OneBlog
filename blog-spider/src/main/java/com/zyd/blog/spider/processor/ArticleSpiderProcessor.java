package com.zyd.blog.spider.processor;

import com.zyd.blog.spider.model.Article;
import com.zyd.blog.spider.model.BaseModel;
import com.zyd.blog.spider.scheduler.BlockingQueueScheduler;
import com.zyd.blog.spider.util.CommonUtil;
import com.zyd.blog.spider.util.WriterUtil;
import com.zyd.blog.spider.webmagic.ZhydSpider;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.proxy.Proxy;
import us.codecraft.webmagic.proxy.SimpleProxyProvider;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 爬虫入口
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/7/23 10:38
 */
public class ArticleSpiderProcessor extends BaseProcessor implements BaseSpider<Article> {

    private BaseModel model;
    private WriterUtil writer;
    private Long uuid;
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    private ArticleSpiderProcessor() {
        this.writer = new WriterUtil();
    }

    public ArticleSpiderProcessor(BaseModel model, WriterUtil writer, Long uuid) {
        super(model);
        this.model = model;
        this.writer = writer;
        this.uuid = uuid;
    }

    public ArticleSpiderProcessor(BaseModel model) {
        super(model);
        this.model = model;
        this.writer = new WriterUtil();
    }

    /**
     * 运行爬虫并返回结果
     *
     * @return
     */
    @Override
    public CopyOnWriteArrayList<Article> run() {
        List<String> errors = validateModel(model);
        if (CollectionUtils.isNotEmpty(errors)) {
            writer.print("校验不通过！请依据下方提示，检查输入参数是否正确......");
            for (String error : errors) {
                writer.print(">> " + error);
            }
            return null;
        }

        writer.print(String.valueOf(CommonUtil.exitWay.get(model.getExitWay()).apply(model.getCount())));
        CopyOnWriteArrayList<Article> articles = new CopyOnWriteArrayList<>();
        ZhydSpider spider = ZhydSpider.create(new ArticleSpiderProcessor(), model, uuid);

        spider.addUrl(model.getEntryUrls())
                .setScheduler(new BlockingQueueScheduler(model))
                .addPipeline((resultItems, task) -> process(resultItems, articles, spider))
                .thread(model.getThreadCount());

        //设置抓取代理IP
        if (!CollectionUtils.isEmpty(model.getProxyList())) {
            HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
            SimpleProxyProvider provider = SimpleProxyProvider.from(model.getProxyList().toArray(new Proxy[0]));
            httpClientDownloader.setProxyProvider(provider);
            spider.setDownloader(httpClientDownloader);
        }
        // 测试代理
        /*HttpClientDownloader httpClientDownloader = new HttpClientDownloader();
        SimpleProxyProvider provider = SimpleProxyProvider.from(
                new Proxy("61.135.217.7", 80)
        );
        httpClientDownloader.setProxyProvider(provider);
        spider.setDownloader(httpClientDownloader);*/

        // 启动爬虫
        spider.run();
        return articles;
    }

    /**
     * 校验参数
     *
     * @param t 待校验的参数
     */
    private <T> List<String> validateModel(T t) {
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

        List<String> messageList = new ArrayList<>();
        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            messageList.add(constraintViolation.getMessage());
        }
        return messageList;
    }

    /**
     * 自定义管道的处理方法
     *
     * @param resultItems 自定义Processor处理完后的所有参数
     * @param articles    爬虫文章集合
     */
    private void process(ResultItems resultItems, List<Article> articles, ZhydSpider spider) {
        if (null == spider) {
            System.out.println("======================爬虫结束了");
            return;
        }
        Map<String, Object> map = resultItems.getAll();
        String title = String.valueOf(map.get("title"));
        if (StringUtils.isEmpty(title) || "null".equals(title)) {
            return;
        }
        String content = String.valueOf(map.get("content"));
        String source = String.valueOf(map.get("source"));
        String releaseDate = String.valueOf(map.get("releaseDate"));
        String author = String.valueOf(map.get("author"));
        String description = CommonUtil.subDescStr(String.valueOf(map.get("description")), content);
        String keywords = CommonUtil.subKeywordsStr(String.valueOf(map.get("keywords")));
        articles.add(new Article(title, content, author, releaseDate, source, description, keywords, (List<String>) map.get("tags")));
        writer.print(String.format("正在抓取 -- <a href=\"%s\" target=\"_blank\">%s</a> -- %s -- %s", source, title, releaseDate, author));
    }


}
