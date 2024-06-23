package com.zyd.blog.controller;

/**
 * 页面渲染相关 -- 页面跳转
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */

import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.Article;
import com.zyd.blog.business.enums.AdPositionEnum;
import com.zyd.blog.business.enums.AdTypeEnum;
import com.zyd.blog.business.service.BizArticleService;
import com.zyd.blog.core.BlogHunterConfigProvider;
import com.zyd.blog.core.websocket.server.ZydWebsocketServer;
import com.zyd.blog.framework.exception.ZhydException;
import com.zyd.blog.util.ResultUtil;
import me.zhyd.hunter.config.platform.Platform;
import me.zhyd.hunter.enums.ExitWayEnum;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;

/**
 * 页面跳转类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/24 14:37
 * @since 1.0
 */
@Controller
public class RenderController {

    @Autowired
    private BizArticleService articleService;
    @Autowired
    private ZydWebsocketServer websocketServer;
    @Autowired
    private BlogHunterConfigProvider blogHunterConfigProvider;

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

    @RequiresPermissions("resources")
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
    @BussinessLog(value = "进入发表文章页[{1}]")
    @GetMapping("/article/publish-{type}")
    public ModelAndView publish(@PathVariable("type") String type) {
        if (!Arrays.asList("we", "md", "tiny").contains(type)) {
            throw new ZhydException("不支持的编辑器类型");
        }
        return ResultUtil.view("article/publish-" + type);
    }

    @RequiresPermissions("article:publish")
    @BussinessLog(value = "进入修改文章页[id={1}]")
    @GetMapping("/article/update/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("id", id);
        Article article = articleService.getByPrimaryKey(id);

        if (!Arrays.asList("we", "md", "tiny").contains(article.getEditorType())) {
            throw new ZhydException("文章异常，未知的编辑器类型");
        }
        return ResultUtil.view("article/publish-" + article.getEditorType());
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

    @RequiresPermissions("icons")
    @BussinessLog(value = "进入icons页")
    @GetMapping("/icons")
    public ModelAndView icons(Model model) {
        return ResultUtil.view("other/icons");
    }

    @RequiresPermissions("shiro")
    @BussinessLog(value = "进入shiro示例页")
    @GetMapping("/shiro")
    public ModelAndView shiro(Model model) {
        return ResultUtil.view("other/shiro");
    }

    @RequiresUser
    @BussinessLog("进入编辑器测试用例页面")
    @GetMapping("/editor")
    public ModelAndView editor(Model model) {
        return ResultUtil.view("other/editor");
    }

    @RequiresPermissions("notice")
    @BussinessLog("进入通知管理页")
    @GetMapping("/notice")
    public ModelAndView notice(Model model) {
        model.addAttribute("online", websocketServer.getOnlineUserCount());
        return ResultUtil.view("laboratory/notification");
    }

    @RequiresUser
    @BussinessLog("进入搬运工页面")
    @GetMapping("/remover")
    public ModelAndView remover(Model model) {
        model.addAttribute("exitWayList", ExitWayEnum.values());
        model.addAttribute("spiderConfig", blogHunterConfigProvider.getBlogHunterConfig());
        model.addAttribute("platforms", Platform.values());
        return ResultUtil.view("laboratory/remover");
    }

    @RequiresPermissions("files")
    @BussinessLog("进入文件管理页面")
    @GetMapping("/files")
    public ModelAndView files(Model model) {
        return ResultUtil.view("file/list");
    }

    @RequiresPermissions("socials")
    @BussinessLog("进入社会化登录配置管理页面")
    @GetMapping("/socials")
    public ModelAndView socials(Model model) {
        return ResultUtil.view("social/list");
    }

    @RequiresPermissions("page")
    @BussinessLog("进入配置自定义页面")
    @GetMapping("/page")
    public ModelAndView page(Model model) {
        return ResultUtil.view("page/page");
    }

    @RequiresPermissions("bizAds")
    @BussinessLog("进入广告页面")
    @GetMapping("/bizAd")
    public ModelAndView bizAd(Model model) {
        model.addAttribute("positions", AdPositionEnum.toListMap());
        model.addAttribute("types", AdTypeEnum.toListMap());
        return ResultUtil.view("bizAd/bizAd");
    }

}
