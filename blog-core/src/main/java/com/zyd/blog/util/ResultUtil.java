package com.zyd.blog.util;

import com.github.pagehelper.PageInfo;
import com.zyd.blog.business.consts.CommonConst;
import com.zyd.blog.framework.object.PageResult;
import com.zyd.blog.business.enums.ResponseStatus;
import com.zyd.blog.framework.object.ResponseVO;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接口返回工具类，支持ModelAndView、ResponseVO、PageResult
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
public class ResultUtil {

    public static ModelAndView view(String view) {
        return new ModelAndView(view);
    }

    public static ModelAndView view(String view, Map<String, Object> model) {
        return new ModelAndView(view, model);
    }

    public static ModelAndView redirect(String view) {
        return new ModelAndView("redirect:" + view);
    }

    public static ResponseVO error(int code, String message) {
        return vo(code, message, null);
    }

    public static ResponseVO error(ResponseStatus status) {
        return vo(status.getCode(), status.getMessage(), null);
    }

    public static ResponseVO error(String message) {
        return vo(CommonConst.DEFAULT_ERROR_CODE, message, null);
    }

    public static ResponseVO success(String message, Object data) {
        return vo(CommonConst.DEFAULT_SUCCESS_CODE, message, data);
    }

    public static ResponseVO success(Object data) {
        return vo(ResponseStatus.SUCCESS.getCode(), ResponseStatus.SUCCESS.getMessage(), data);
    }

    public static ResponseVO success(String message) {
        return success(message, null);
    }

    public static ResponseVO success(ResponseStatus status) {
        return vo(status.getCode(), status.getMessage(), null);
    }

    public static ResponseVO vo(int code, String message, Object data) {
        return new ResponseVO<>(code, message, data);
    }

    public static PageResult tablePage(Long total, List<?> list) {
        return new PageResult(total, list);
    }

    public static PageResult tablePage(PageInfo info) {
        if (info == null) {
            return new PageResult(0L, new ArrayList());
        }
        return tablePage(info.getTotal(), info.getList());
    }

}
