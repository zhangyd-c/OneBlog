package com.zyd.blog.business.aspect;

import com.alibaba.fastjson.JSONObject;
import com.zyd.blog.business.annotation.BussinessLog;
import com.zyd.blog.business.entity.Log;
import com.zyd.blog.business.entity.User;
import com.zyd.blog.business.enums.LogLevelEnum;
import com.zyd.blog.business.enums.LogTypeEnum;
import com.zyd.blog.business.enums.PlatformEnum;
import com.zyd.blog.business.service.SysLogService;
import com.zyd.blog.business.util.WebSpiderUtils;
import com.zyd.blog.util.AspectUtil;
import com.zyd.blog.util.RequestUtil;
import com.zyd.blog.util.SessionUtil;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * AOP切面记录日志
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class BussinessLogAspect {

    @Autowired
    private SysLogService logService;

    @Pointcut(value = "@annotation(com.zyd.blog.business.annotation.BussinessLog)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object writeLog(ProceedingJoinPoint point) throws Throwable {

        //先执行业务
        Object result = point.proceed();

        try {
            handle(point);
        } catch (Exception e) {
            log.error("日志记录出错!", e);
        }

        return result;
    }

    private void handle(ProceedingJoinPoint point) throws Exception {
        Method currentMethod = AspectUtil.INSTANCE.getMethod(point);
        //获取操作名称
        BussinessLog annotation = currentMethod.getAnnotation(BussinessLog.class);
        boolean save = annotation.save();
        PlatformEnum platform = annotation.platform();
        String bussinessName = AspectUtil.INSTANCE.parseParams(point.getArgs(), annotation.value());
        String ua = RequestUtil.getUa();

        log.info("{} | {} - {} {} - {}", bussinessName, RequestUtil.getIp(), RequestUtil.getMethod(), RequestUtil.getRequestUrl(), ua);
        if (!save) {
            return;
        }
        Log sysLog = new Log();
        sysLog.setLogLevel(LogLevelEnum.INFO);
        sysLog.setType(platform.equals(PlatformEnum.WEB) ? LogTypeEnum.VISIT : LogTypeEnum.SYSTEM);
        sysLog.setIp(RequestUtil.getIp());
        sysLog.setReferer(RequestUtil.getReferer());
        sysLog.setRequestUrl(RequestUtil.getRequestUrl());
        sysLog.setUa(ua);
        sysLog.setSpiderType(WebSpiderUtils.parseUa(ua));
        sysLog.setParams(JSONObject.toJSONString(RequestUtil.getParametersMap()));
        User user = SessionUtil.getUser();
        if (user != null) {
            sysLog.setUserId(user.getId());
            sysLog.setContent(String.format("用户: [%s] | 操作: %s", user.getUsername(), bussinessName));
        } else {
            sysLog.setContent(String.format("访客: [%s] | 操作: %s", sysLog.getIp(), bussinessName));
        }

        try {
            UserAgent agent = UserAgent.parseUserAgentString(ua);
            sysLog.setBrowser(agent.getBrowser().getName());
            sysLog.setOs(agent.getOperatingSystem().getName());
            logService.insert(sysLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
