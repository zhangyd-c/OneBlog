package com.zyd.blog.controller;

import com.zyd.blog.business.entity.Page;
import com.zyd.blog.business.service.BizPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yadong.zhang email:yadong.zhang(a)innodev.com.cn
 * @version 1.0
 * @date 2021/10/23 00:19
 * @since 1.0
 */
@RestController
public class RestPageController {

    @Autowired
    private BizPageService bizPageService;

    @GetMapping(value = "/{filename}.xml", produces = {"application/xml"})
    public String xml(@PathVariable("filename") String filename) {
        return this.getContent(filename + ".xml");
    }

    @GetMapping(value = "/{filename}.txt", produces = {"text/plain"})
    public String txt(@PathVariable("filename") String filename) {
        return this.getContent(filename + ".txt");
    }

    @GetMapping(value = "/.well-known/{url}/{filename}.txt", produces = {"text/plain"})
    public String multiPath(@PathVariable("url") String url, @PathVariable("filename") String filename) {
        return this.getContent(".well-known/" + url + "/" + filename + ".txt");
    }

    @GetMapping(value = "/{filename}.html", produces = {"text/html"})
    public String html(@PathVariable("filename") String filename) {
        return this.getContent(filename + ".html");
    }

    private String getContent(String url) {
        Page page = bizPageService.getByUrl(url);
        return null == page ? "" : page.getContent();
    }

}
