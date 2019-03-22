package com.zyd.blog.util;

import com.zyd.blog.framework.holder.RequestHolder;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
public class RequestUtil {

    public static String getParameters() {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return null;
        }
        Enumeration<String> paraNames = request.getParameterNames();
        if (paraNames == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        while (paraNames.hasMoreElements()) {
            String paraName = paraNames.nextElement();
            sb.append("&").append(paraName).append("=").append(request.getParameter(paraName));
        }
        return sb.toString();
    }

    public static Map<String, Object> getParametersMap() {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return new HashMap<>();
        }
        Enumeration<String> paraNames = request.getParameterNames();
        if (paraNames == null) {
            return new HashMap<>();
        }
        Map<String, Object> res = new HashMap<>();
        while (paraNames.hasMoreElements()) {
            String paraName = paraNames.nextElement();
            res.put(paraName, request.getParameter(paraName));
        }
        return res;
    }

    public static String getHeader(String headerName) {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return null;
        }
        return request.getHeader(headerName);
    }

    public static String getReferer() {
        return getHeader("Referer");
    }

    public static String getUa() {
        return getHeader("User-Agent");
    }

    public static String getIp() {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return null;
        }
        return IpUtil.getRealIp(request);
    }

    public static String getRequestUrl() {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return null;
        }
        return request.getRequestURL().toString();
    }

    public static String getMethod() {
        HttpServletRequest request = RequestHolder.getRequest();
        if (null == request) {
            return null;
        }
        return request.getMethod();
    }

    public static boolean isAjax(HttpServletRequest request) {
        if (null == request) {
            request = RequestHolder.getRequest();
        }
        if (null == request) {
            return false;
        }
        return "XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))
                || request.getParameter("ajax") != null;

    }

}
