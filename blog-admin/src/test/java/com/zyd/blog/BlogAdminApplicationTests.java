package com.zyd.blog;

import com.zyd.blog.business.enums.QiniuUploadType;
import com.zyd.blog.plugin.QiniuApi;
import com.zyd.blog.spider.model.*;
import com.zyd.blog.spider.processor.ArticleSpiderProcessor;
import com.zyd.blog.spider.processor.DrugSpiderProcessor;
import com.zyd.blog.spider.processor.ZydSpider;
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
    public void drugSpiderTest(){
        DrugSpiderProcessor.run();
    }

    @Test
    public void spiderTest() {
        ZydSpider<Article> spider = new ArticleSpiderProcessor(new ImoocModel().setUid("1175248")
                .setTotalPage(3)
                .setDomain("www.imooc.com")
                .setTitleRegex("//span[@class=js-title]/html()")
                .setAuthorRegex("//div[@class=name_con]/p[@class=name]/a[@class=nick]/html()")
                .setReleaseDateRegex("//div[@class='dc-profile']/div[@class='l']/span[@class='spacer']/text()")
                .setContentRegex("//div[@class=detail-content]/html()")
                .setTargetLinksRegex("/article/[0-9]{1,10}")
                .setTagRegex("//div[@class=cat-box]/div[@class=cat-wrap]/a[@class=cat]/html()")

                .setCookie("IMCDNS=0; imooc_uuid=e7a46d50-1d50-4b67-8a4f-20d56001de3c; imooc_isnew=1; imooc_isnew_ct=1533286936; loginstate=1; apsid=IxZDQ4ZDI2YzQ3YTdmODFjZmI5N2U1YjY1YjhhNDUAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAMTE3NTI0OAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAByeHh6eWQxMTIzQDE2My5jb20AAAAAAAAAAAAAAAAAAGEyZjc3YmZiYjhhNzQ4NmI4MGMyZDkyZDk2ZGQ4ZmEzJhpkWyYaZFs%3DYm; last_login_username=rxxzyd1123%40163.com; PHPSESSID=a7316sajatt1ud76v2ld5g2db2; cvde=5b641a18ad45b-47")

                .setHeader("Host", "www.imooc.com")
                .setHeader("Referer", "https://www.imooc.com"));
        spider.run();
    }

    @Test
    public void csdnSpiderTest() {
        ZydSpider<Article> spider = new ArticleSpiderProcessor(new CsdnModel().setUid("u011197448")
                .setTotalPage(1)
                .setDomain("blog.csdn.net")

                .setTitleRegex("//h1[@class=title-article]/html()")
                .setAuthorRegex("//div[@class=profile-intro]/div[@class=user-info]/p[@class=name]/a[@class=text-truncate]/html()")
                .setReleaseDateRegex("//div[@class='article-bar-top']/span[@class='time']/text()")
                .setContentRegex("//div[@class=article_content]/html()")
                .setTargetLinksRegex(".*blog\\.csdn\\.net/u011197448/article/details/[0-9a-zA-Z]{1,15}")
                .setTagRegex("//span[@class=artic-tag-box]/a[@class=tag-link]/html()")

                .setCookie("uuid_tt_dd=10_18752534250-1532653661936-548523; __yadk_uid=Azckmtol9B3Q1677fAFIbpA9VhKbK5Ge; Hm_ct_6bcd52f51e9b3dce32bec4a3997715ac=1788*1*PC_VC; smidV2=2018073110202411619e299fa87e58a6d2fa513eb0560100370be57076aab70; UN=u011197448; Hm_lvt_6bcd52f51e9b3dce32bec4a3997715ac=1533203845,1533264901,1533273617,1533281367; dc_session_id=10_1533284174899.975225; UserName=u011197448; UserInfo=Ht4eplKngPM%2BlqynD7AUN60KU8guuVQBsxDzDuvws4EYrbUMrVqt11By0pOsylwQDSz%2FSHACu5aKgWpIqB23EEdftuzXlq3O4vBqE4BXrKs2LsQwkx5jpcpry0Ju%2BUNG; UserNick=%E4%B8%83%E5%BD%A9%E7%8B%BC; AU=9FA; BT=1533284260805; UserToken=Ht4eplKngPM%2BlqynD7AUN60KU8guuVQBsxDzDuvws4EYrbUMrVqt11By0pOsylwQDSz%2FSHACu5aKgWpIqB23EEdftuzXlq3O4vBqE4BXrKurrkHcSkadZ3mIAfmpjySmT5zXMhwfoVAQ3iLltdps42y6%2BBczwI00vyyjJhe6p8p543v2LHcx4Be6xzB43XhR; TY_SESSION_ID=f2813db8-9622-4d89-9242-45e76f4fcbd7; dc_tos=pcvn4x")

                .setHeader("Host", "blog.csdn.net")
                .setHeader("Referer", "https://blog.csdn.net/u011197448/article/list/1"));
        spider.run();
    }

    @Test
    public void iteyeSpiderTest() {
        ZydSpider<Article> spider = new ArticleSpiderProcessor(new IteyeModel().setUid("843977358")
                .setTotalPage(1)
                .setDomain("843977358.iteye.com")

                .setTitleRegex("//div[@class=blog_title]/h3/a/html()")
                .setAuthorRegex("//div[@id=blog_owner_name]/html()")
                .setReleaseDateRegex("//div[@class=blog_bottom]/ul/li/html()")
                .setContentRegex("//div[@class=blog_content]/html()")
                .setTargetLinksRegex(".*843977358\\.iteye\\.com/blog/[0-9]+")
                .setTagRegex("//div[@class=news_tag]/a/html()")

                .setCookie("_javaeye_cookie_id_=1533347307845341; dc_session_id=1533711983437_0.44695905064454156; dc_tos=pd4rx2; _javaeye_cookie_id_=1533347307845341; _javaeye3_session_=BAh7CDoMdXNlcl9pZGkDxvkXOhBfY3NyZl90b2tlbiIxV09DNDZKS3hPbjVwK2RJRkxmRURkM09CU2hRYld2UFY4MTc1bFNCZmlwbz06D3Nlc3Npb25faWQiJTZlYWQ0MTJjYTEzNjNiMWE0YjUxNDQ3ZmEzY2ZmMGRi--cd2bd92e73d22ce4c75e48e2d211f72c59b469d1")

                .setHeader("Host", "843977358.iteye.com")
                .setHeader("Referer", "http://843977358.iteye.com/"));
        spider.run();
    }
    @Test
    public void spring4AllSpiderTest() {
        /*ZydSpider<Article> spider = new ArticleSpiderProcessor(new BaseModel()
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
