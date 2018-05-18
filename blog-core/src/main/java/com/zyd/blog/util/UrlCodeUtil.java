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
package com.zyd.blog.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * UrlEncoder & UrlDecoder
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/18 11:48
 * @since 1.0
 */
@Slf4j
public class UrlCodeUtil {

    private static final String URL_ENCODE = "UTF-8";

    private UrlCodeUtil() {
    }

    public static String encode(String param) {
        return encode(param, URL_ENCODE);
    }

    public static String encode(String param, String encode) {
        String result = null;
        if (!StringUtils.isEmpty(param)) {
            try {
                result = URLEncoder.encode(param, encode);
            } catch (UnsupportedEncodingException e) {
                log.error("url编码发生异常", e);
            }
        }
        return result;
    }

    public static String decode(String param) {
        return decode(param, URL_ENCODE);
    }

    public static String decode(String param, String encode) {
        String result = null;
        if (!StringUtils.isEmpty(param)) {
            try {
                result = URLDecoder.decode(param, encode);
            } catch (UnsupportedEncodingException e) {
                log.error("url编码发生异常", e);
            }
        }
        return result;
    }
}
