/**
 * MIT License
 *
 * Copyright (c) 2018 yadong.zhang
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.business.enums;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public enum ResponseStatus {

    SUCCESS(200, "操作成功！"),
    ERROR(500, "服务器未知错误！"),
    UNAUTHORIZED(500, "尚未登录！"),
    FORBIDDEN(500, "您没有操作权限！"),
    NOT_FOUND(500, "资源不存在！"),
    LOGIN_ERROR(500, "账号或密码错误！"),
    USER_EXIST(500, "已存在的用户！"),
    INVALID_AUTHCODE(500, "手机验证码无效！"),
    INVALID_TOKEN(500, "无效的TOKEN，您没有操作权限！"),
    INVALID_ACCESS(500, "无效的请求，该请求已过期！"),
    DELETE_ERROR(500, "删除失败！");

    private Integer code;
    private String message;

    ResponseStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseStatus getResponseStatus(String message) {
        for (ResponseStatus ut : ResponseStatus.values()) {
            if (ut.getMessage() == message) {
                return ut;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
