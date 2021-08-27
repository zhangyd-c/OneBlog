package com.zyd.blog.business.service.impl;

import com.zyd.blog.business.entity.Comment;
import com.zyd.blog.business.entity.Link;
import com.zyd.blog.business.entity.MailDetail;
import com.zyd.blog.business.entity.Template;
import com.zyd.blog.business.enums.ConfigKeyEnum;
import com.zyd.blog.business.enums.TemplateKeyEnum;
import com.zyd.blog.business.service.MailService;
import com.zyd.blog.business.service.SysConfigService;
import com.zyd.blog.business.service.SysTemplateService;
import com.zyd.blog.util.FreeMarkerUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 邮件发送
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SysTemplateService templateService;
    @Autowired
    private SysConfigService configService;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 普通的发送
     *
     * @param mailDetail
     * @return
     */
    @Override
    @Async
    public void send(MailDetail mailDetail) {
        sendMessage(mailDetail, from);
    }

    /**
     * 发送友情链接邮件通知
     *
     * @param link
     * @param keyEnum
     * @return
     */
    @Override
    @Async
    public void send(Link link, TemplateKeyEnum keyEnum) {
        if (!StringUtils.isEmpty(link.getEmail())) {
            Map config = configService.getConfigs();
            Template template = templateService.getTemplate(keyEnum);
            String temXml = template.getRefValue();
            Map<String, Object> map = new HashMap<>(2);
            map.put("link", link);
            map.put("config", config);
            String mailContext = FreeMarkerUtil.template2String(temXml, map, true);

            MailDetail mailDetail = new MailDetail("友情链接操作通知", link.getEmail(), link.getName(), mailContext);
            send(mailDetail);
        }
        this.sendToAdmin(link);
    }

    /**
     * 发送评论邮件通知
     *
     * @param comment
     * @param keyEnum
     * @param audit
     * @return
     */
    @Override
    @Async
    public void send(Comment comment, TemplateKeyEnum keyEnum, boolean audit) {
        if (comment == null || StringUtils.isEmpty(comment.getEmail())) {
            this.sendToAdmin(comment);
            return;
        }
        Map config = configService.getConfigs();
        Template template = templateService.getTemplate(keyEnum);
        String temXml = template.getRefValue();
        Map<String, Object> map = new HashMap<>(2);
        map.put("comment", comment);
        map.put("config", config);
        String mailContext = FreeMarkerUtil.template2String(temXml, map, true);
        String subject = "评论回复通知";
        if (audit) {
            subject = "评论审核结果通知";
        }
        MailDetail mailDetail = new MailDetail(subject, comment.getEmail(), comment.getNickname(), mailContext);
        send(mailDetail);
        if (!audit) {
            this.sendToAdmin(comment);
        }
    }

    /**
     * 发送到管理员的友链操作通知
     *
     * @param link
     */
    @Override
    @Async
    public void sendToAdmin(Link link) {
        Map config = configService.getConfigs();
        Template template = templateService.getTemplate(TemplateKeyEnum.TM_LINKS_TO_ADMIN);
        String temXml = template.getRefValue();
        Map<String, Object> map = new HashMap<>(1);
        map.put("link", link);
        String mailContext = FreeMarkerUtil.template2String(temXml, map, true);
        String adminEmail = (String) config.get(ConfigKeyEnum.AUTHOR_EMAIL.getKey());
        if (StringUtils.isEmpty(adminEmail)) {
            log.warn("[sendToAdmin]邮件发送失败！未指定系统管理员的邮箱地址");
            return;
        }
        adminEmail = (adminEmail.contains("#") ? adminEmail.replace("#", "@") : adminEmail);
        MailDetail mailDetail = new MailDetail("有新的友链消息", adminEmail, (String) config.get(ConfigKeyEnum.AUTHOR_NAME.getKey()), mailContext);
        send(mailDetail);
    }

    /**
     * 发送到管理员的评论通知
     *
     * @param comment
     */
    @Override
    @Async
    public void sendToAdmin(Comment comment) {
        Map config = configService.getConfigs();
        Template template = templateService.getTemplate(TemplateKeyEnum.TM_NEW_COMMENT);
        String temXml = template.getRefValue();
        Map<String, Object> map = new HashMap<>(2);
        map.put("comment", comment);
        map.put("config", config);
        String mailContext = FreeMarkerUtil.template2String(temXml, map, true);
        String adminEmail = (String) config.get("authorEmail");
        if (StringUtils.isEmpty(adminEmail)) {
            log.warn("[sendToAdmin]邮件发送失败！未指定系统管理员的邮箱地址");
            return;
        }
        adminEmail = (adminEmail.contains("#") ? adminEmail.replace("#", "@") : adminEmail);
        MailDetail mailDetail = new MailDetail("有新的评论消息", adminEmail, (String) config.get("authorName"), mailContext);
        send(mailDetail);
    }

    private void sendMessage(MailDetail detail, String from) {
        log.info("Start to send html email for [{}({})]", detail.getToUsername(), detail.getToMailAddress());
        if (StringUtils.isEmpty(detail.getToMailAddress())) {
            log.warn("邮件接收者为空！");
            return;
        }
        MimeMessage message = null;
        try {
            message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            InternetAddress fromAddress = new InternetAddress(MimeUtility.encodeText("网站管理员") + "<" + from + ">");
            helper.setFrom(fromAddress);
            InternetAddress toAddress = new InternetAddress(MimeUtility.encodeText(detail.getToMailAddress()) + "<" + detail.getToMailAddress() + ">");
            helper.setTo(toAddress);
            helper.setSubject(detail.getSubject());
            helper.setText(detail.getContent(), detail.isHtml());
            if (detail.getCc() != null && detail.getCc().length > 0) {
                helper.setCc(detail.getCc());
            }
            javaMailSender.send(message);
        } catch (MessagingException | UnsupportedEncodingException e) {
            log.error("Failed to send E-mail. e [{}]", e.getMessage());
        }
    }
}
