/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.controller;

/**
 * 页面渲染相关 -- 页面跳转
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */

import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.util.ResultUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

/**
 * 页面跳转类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@Controller
public class RenderController {

    @Autowired
    private BizArticleService articleService;

    @RequiresAuthentication
    @BussinessLog("进入首页")
    @GetMapping(value = {""})
    public ModelAndView home() {
        return ResultUtil.view("index");
    }

    @RequiresPermissions("users")
    @BussinessLog("进入用户列表页")
    @GetMapping("/users")
    public ModelAndView user() {
        return ResultUtil.view("user/list");
    }

    @RequiresPermissions("users")
    @BussinessLog("进入资源列表页")
    @GetMapping("/resources")
    public ModelAndView resources() {
        return ResultUtil.view("resources/list");
    }

    @RequiresPermissions("roles")
    @BussinessLog("进入角色列表页")
    @GetMapping("/roles")
    public ModelAndView roles() {
        return ResultUtil.view("role/list");
    }

    @RequiresPermissions("articles")
    @BussinessLog("进入文章列表页")
    @GetMapping("/articles")
    public ModelAndView articles() {
        return ResultUtil.view("article/list");
    }

    @RequiresPermissions("article:publish")
    @BussinessLog(value = "发表文章页[html]")
    @GetMapping("/article/publish")
    public ModelAndView publish() {
        return ResultUtil.view("article/publish");
    }

    @RequiresPermissions("article:publish")
    @BussinessLog(value = "发表文章页[markdown]")
    @GetMapping("/article/publishMd")
    public ModelAndView publishMd() {
        return ResultUtil.view("article/publish-md");
    }

    @RequiresPermissions("article:publish")
    @BussinessLog(value = "修改文章页[id={1}]")
    @GetMapping("/article/update/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        Article article = articleService.getByPrimaryKey(id);
        if(article.getIsMarkdown()){
            return ResultUtil.view("article/publish-md");
        }
        return ResultUtil.view("article/publish");
    }

    @RequiresPermissions("types")
    @BussinessLog("进入分类列表页")
    @GetMapping("/article/types")
    public ModelAndView types() {
        return ResultUtil.view("article/types");
    }

    @RequiresPermissions("tags")
    @BussinessLog("进入标签列表页")
    @GetMapping("/article/tags")
    public ModelAndView tags() {
        return ResultUtil.view("article/tags");
    }

    @RequiresPermissions("links")
    @BussinessLog("进入链接页")
    @GetMapping("/links")
    public ModelAndView links() {
        return ResultUtil.view("link/list");
    }

    @RequiresPermissions("comments")
    @BussinessLog("进入评论页")
    @GetMapping("/comments")
    public ModelAndView comments() {
        return ResultUtil.view("comment/list");
    }

    @RequiresPermissions("notices")
    @BussinessLog("进入系统通知页")
    @GetMapping("/notices")
    public ModelAndView notices() {
        return ResultUtil.view("notice/list");
    }

    @RequiresRoles("role:root")
    @BussinessLog("进入系统配置页")
    @GetMapping("/config")
    public ModelAndView config() {
        return ResultUtil.view("config");
    }

    @RequiresPermissions("templates")
    @BussinessLog("进入模板管理页")
    @GetMapping("/templates")
    public ModelAndView templates() {
        return ResultUtil.view("template/list");
    }

    @RequiresPermissions("updateLogs")
    @BussinessLog("进入更新记录管理页")
    @GetMapping("/updates")
    public ModelAndView updates() {
        return ResultUtil.view("update/list");
    }

    @RequiresPermissions("plays")
    @BussinessLog("进入歌单管理页")
    @GetMapping("/plays")
    public ModelAndView plays() {
        return ResultUtil.view("play/list");
    }

    @RequiresPermissions("sysWebpage")
    @BussinessLog("进入静态页面管理页")
    @GetMapping("/sysWebpage")
    public ModelAndView sysWebpage() {
        return ResultUtil.view("sysWebpage/list");
    }

    @RequiresPermissions("icons")
    @GetMapping("/icons")
    public ModelAndView icons(Model model) {
        return ResultUtil.view("icons");
    }

    @RequiresPermissions("shiro")
    @GetMapping("/shiro")
    public ModelAndView shiro(Model model) {
        return ResultUtil.view("shiro");
    }
}
