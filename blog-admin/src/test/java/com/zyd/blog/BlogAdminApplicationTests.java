package com.zyd.blog;

import com.zyd.blog.business.enums.QiniuUploadType;
import com.zyd.blog.plugin.QiniuApi;
import com.zyd.blog.spider.enums.ExitWayEnum;
import com.zyd.blog.spider.model.*;
import com.zyd.blog.spider.processor.ArticleSpiderProcessor;
import com.zyd.blog.spider.processor.BaseSpider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BlogAdminApplicationTests {

    @Test
    public void contextLoads() {
        File file = new File("C:\\x\\x.png");
        try {
            String filePath = QiniuApi.getInstance()
                    .withFileName(file.getName(), QiniuUploadType.SIMPLE)
                    .upload(file);
            System.out.println(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void imoocSpiderTest() {
        BaseSpider<VirtualArticle> spider = new ArticleSpiderProcessor(new BaseModel().setUid("1175248")
                .setEntryUrls("https://www.imooc.com/u/1175248/articles?page=1")
                .setDomain("www.imooc.com")
                .setTitleRegex("//span[@class=js-title]/html()")
                .setAuthorRegex("//div[@class=name_con]/p[@class=name]/a[@class=nick]/html()")
                .setReleaseDateRegex("//div[@class='dc-profile']/div[@class='l']/span[@class='spacer']/text()")
                .setContentRegex("//div[@class=detail-content]/html()")
                .setTargetLinksRegex("/article/[0-9]{1,10}")
                .setTagRegex("//div[@class=cat-box]/div[@class=cat-wrap]/a[@class=cat]/html()")
                .setHeader("Host", "www.imooc.com")
                .setHeader("Referer", "https://www.imooc.com")
                .setExitWay(ExitWayEnum.URL_COUNT)
                .setCount(5));
        spider.run();
    }

    @Test
    public void csdnSpiderTest() {
        BaseSpider<VirtualArticle> spider = new ArticleSpiderProcessor(new BaseModel().setUid("u011197448")
                .setEntryUrls("https://blog.csdn.net/u011197448/article/list/1")
                .setDomain("blog.csdn.net")
                .setTitleRegex("//h1[@class=title-article]/html()")
                .setAuthorRegex("//div[@class=profile-intro]/div[@class=user-info]/p[@class=name]/a[@class=text-truncate]/html()")
                .setReleaseDateRegex("//div[@class='article-bar-top']/span[@class='time']/text()")
                .setContentRegex("//div[@class=article_content]/html()")
                .setTargetLinksRegex(".*blog\\.csdn\\.net/u011197448/article/details/[0-9a-zA-Z]{1,15}")
                .setTagRegex("//span[@class=artic-tag-box]/a[@class=tag-link]/html()")
                .setHeader("Host", "blog.csdn.net")
                .setHeader("Referer", "https://blog.csdn.net/u011197448/article/list/1")
                .setExitWay(ExitWayEnum.DURATION)
                .setCount(5));
        spider.run();
    }

    @Test
    public void iteyeSpiderTest() {
        BaseSpider<VirtualArticle> spider = new ArticleSpiderProcessor(new BaseModel().setUid("843977358")
                .setEntryUrls("http://843977358.iteye.com/?page=1")
                .setDomain("843977358.iteye.com")
                .setTitleRegex("//div[@class=blog_title]/h3/a/html()")
                .setAuthorRegex("//div[@id=blog_owner_name]/html()")
                .setReleaseDateRegex("//div[@class=blog_bottom]/ul/li/html()")
                .setContentRegex("//div[@class=blog_content]/html()")
                .setTargetLinksRegex(".*843977358\\.iteye\\.com/blog/[0-9]+")
                .setTagRegex("//div[@class=news_tag]/a/html()")
                .setHeader("Host", "843977358.iteye.com")
                .setHeader("Referer", "http://843977358.iteye.com/")
                .setExitWay(ExitWayEnum.URL_COUNT)
                .setCount(5));
        spider.run();
    }


    @Test
    public void cnblogSpiderTest() {
        BaseSpider<VirtualArticle> spider = new ArticleSpiderProcessor(new BaseModel().setUid("zhangyadong")
                .setEntryUrls("https://www.cnblogs.com/zhangyadong/default.html?page=1")
                .setDomain("www.cnblogs.com")
                .setTitleRegex("//a[@id=cb_post_title_url]/html()")
                .setAuthorRegex("//div[@class=postDesc]/a[1]/html()")
                .setReleaseDateRegex("//span[@id=post-date]/html()")
                .setContentRegex("//div[@id=cnblogs_post_body]/html()")
                .setTagRegex("//div[@id=EntryTag]/a/html()")
                .setTargetLinksRegex(".*www\\.cnblogs\\.com/zhangyadong/p/[\\w\\d]+\\.html")
                .setHeader("Host", "www.cnblogs.com")
                .setHeader("Referer", "https://www.cnblogs.com/"));
        spider.run();
    }

    @Test
    public void spring4AllSpiderTest() {
        /*BaseSpider<VirtualArticle> spider = new ArticleSpiderProcessor(new BaseModel()
                .setEntryUrls("http://www.spring4all.com/user/api/articles/1")
                .setAjaxRequest(true)
                .setDomain("www.spring4all.com")

                .setTitleRegex("$.data.list.title")
                .setAuthorRegex("$.data.list.nickname")
                .setReleaseDateRegex("$.data.list.createTime")
                .setContentRegex("$.data.list.title")
                .setTargetLinksRegex(".*www\\.spring4all\\.com/user/api/articles/[0-9]+")

                .setCookie("")

                .setHeader("Host", "www.spring4all.com")
                .setHeader("Referer", "http://www.spring4all.com/user"));
        spider.run();*/
    }


}
