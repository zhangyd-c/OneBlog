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

    @GetMapping(value = "/{url}.xml", produces = {"application/xml"})
    public String xml(@PathVariable("url") String url) {
        return this.getContent(url + ".xml");
    }

    @GetMapping(value = "/{url}.txt", produces = {"text/plain"})
    public String txt(@PathVariable("url") String url) {
        return this.getContent(url + ".txt");
    }

    @GetMapping(value = "/{url}.html", produces = {"text/html"})
    public String html(@PathVariable("url") String url) {
        return this.getContent(url + ".html");
    }

    private String getContent(String url) {
        Page page = bizPageService.getByUrl(url);
        return null == page ? "" : page.getContent();
    }

}
