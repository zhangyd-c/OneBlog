package com.zyd.blog.controller;

import com.fujieid.jap.core.JapUser;
import com.fujieid.jap.core.JapUserService;
import com.fujieid.jap.core.config.JapConfig;
import com.fujieid.jap.core.context.JapAuthentication;
import com.fujieid.jap.core.result.JapResponse;
import com.fujieid.jap.social.SocialStrategy;
import com.zyd.blog.business.entity.SocialConfig;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.service.SysSocialConfigService;
import com.zyd.blog.framework.exception.ZhydException;
import com.zyd.blog.plugin.oauth.JapUtil;
import com.zyd.blog.util.ResultUtil;
import com.zyd.blog.util.SessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2019/2/19 9:28
 * @since 1.8
 */
@Controller
@RequestMapping("/oauth")
public class OAuthController {

    @Autowired
    private SysSocialConfigService sysSocialConfigService;
    @Autowired
    private JapUserService japUserService;

    @RequestMapping("/social/{source}")
    public ModelAndView renderAuth(@PathVariable("source") String source, HttpServletResponse response, HttpServletRequest request) {
        SocialConfig socialConfig = sysSocialConfigService.getByPlatform(source);
        if (null == socialConfig) {
            throw new ZhydException(source + " 平台的配置尚未完成，暂时不支持登录！");
        }
        SocialStrategy socialStrategy = new SocialStrategy(japUserService, new JapConfig());
        JapResponse japResponse = socialStrategy.authenticate(JapUtil.blogSocialConfig2JapSocialConfig(socialConfig, source), request, response);
        if (!japResponse.isSuccess()) {
            throw new ZhydException(japResponse.getMessage());
        }
        if (japResponse.isRedirectUrl()) {
            return ResultUtil.redirect((String) japResponse.getData());
        } else {
            JapUser japUser = (JapUser) japResponse.getData();
            User user = (User) japUser.getAdditional();
            SessionUtil.setUser(user);
            return ResultUtil.redirect("/");
        }
    }

    /**
     * 退出登录
     */
    @RequestMapping("/logout")
    public ModelAndView logout(HttpServletResponse response, HttpServletRequest request) {
        JapAuthentication.logout(request, response);
        SessionUtil.removeUser();
        return ResultUtil.redirect("/");
    }

}
