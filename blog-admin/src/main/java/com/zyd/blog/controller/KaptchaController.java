package com.zyd.blog.controller;

import com.zyd.blog.plugin.kaptcha.Captcha;
import com.zyd.blog.plugin.kaptcha.GifCaptcha;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
public class KaptchaController {
    private static final Logger LOG = LoggerFactory.getLogger(KaptchaController.class);

    @GetMapping("/getKaptcha")
    @ResponseBody
    public void getKaptcha(HttpServletResponse response) {
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(146,33,4);
            //输出
            captcha.out(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
            LOG.error("获取验证码异常：{}", e.getMessage());
        }
    }

}
