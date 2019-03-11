package com.zyd.blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 重写BasicErrorController,主要负责系统的异常页面的处理以及错误信息的显示
 * <p/>
 * 此处指需要记录
 * <p/>
 * 要注意，这个类里面的代码一定不能有异常或者潜在异常发生，否则可能会让程序陷入死循环。
 * <p/>
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Controller
@RequestMapping("/error")
@EnableConfigurationProperties({ServerProperties.class})
public class ErrorPagesController implements ErrorController {

    private ErrorAttributes errorAttributes;

    @Autowired
    private ServerProperties serverProperties;

    /**
     * 初始化ExceptionController
     *
     * @param errorAttributes
     */
    @Autowired
    public ErrorPagesController(ErrorAttributes errorAttributes) {
        Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
        this.errorAttributes = errorAttributes;
    }

    @RequestMapping("/404")
    public ModelAndView errorHtml404(HttpServletRequest request, HttpServletResponse response, WebRequest webRequest) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        Map<String, Object> model = getErrorAttributes(webRequest, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("queryString", request.getQueryString());

        return new ModelAndView("error/404", model);
    }

    @RequestMapping("/403")
    public ModelAndView errorHtml403(HttpServletRequest request, HttpServletResponse response, WebRequest webRequest) {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        // 404拦截规则，如果是静态文件发生的404则不记录到DB
        Map<String, Object> model = getErrorAttributes(webRequest, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("queryString", request.getQueryString());
        if (!String.valueOf(model.get("path")).contains(".")) {
            model.put("status", HttpStatus.FORBIDDEN.value());
        }
        return new ModelAndView("error/403", model);
    }

    @RequestMapping("/400")
    public ModelAndView errorHtml400(HttpServletRequest request, HttpServletResponse response, WebRequest webRequest) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        Map<String, Object> model = getErrorAttributes(webRequest, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("queryString", request.getQueryString());
        return new ModelAndView("error/400", model);
    }

    @RequestMapping("/401")
    public ModelAndView errorHtml401(HttpServletRequest request, HttpServletResponse response, WebRequest webRequest) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        Map<String, Object> model = getErrorAttributes(webRequest, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("queryString", request.getQueryString());
        return new ModelAndView("error/401", model);
    }

    @RequestMapping("/500")
    public ModelAndView errorHtml500(HttpServletRequest request, HttpServletResponse response, WebRequest webRequest) {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        Map<String, Object> model = getErrorAttributes(webRequest, isIncludeStackTrace(request, MediaType.TEXT_HTML));
        model.put("queryString", request.getQueryString());
        return new ModelAndView("error/500", model);
    }

    /**
     * Determine if the stacktrace attribute should be included.
     *
     * @param request
     *         the source request
     * @param produces
     *         the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request,
                                          MediaType produces) {
        ErrorProperties.IncludeStacktrace include = this.serverProperties.getError().getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        return include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM && getTraceParameter(request);
    }


    /**
     * 获取错误的信息
     *
     * @param webRequest
     * @param includeStackTrace
     * @return
     */
    private Map<String, Object> getErrorAttributes(WebRequest webRequest,
                                                   boolean includeStackTrace) {
        return this.errorAttributes.getErrorAttributes(webRequest, includeStackTrace);
    }

    /**
     * 是否包含trace
     *
     * @param request
     * @return
     */
    private boolean getTraceParameter(HttpServletRequest request) {
        String parameter = request.getParameter("trace");
        return parameter != null && !"false".equalsIgnoreCase(parameter);
    }

    /**
     * 获取错误编码
     *
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request
                .getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        try {
            return HttpStatus.valueOf(statusCode);
        } catch (Exception ex) {
            log.error("获取当前HttpStatus发生异常", ex);
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }

    /**
     * 实现错误路径,暂时无用
     *
     * @return
     */
    @Override
    public String getErrorPath() {
        return "";
    }
}
