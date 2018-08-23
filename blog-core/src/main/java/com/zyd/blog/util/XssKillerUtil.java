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

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/8/07 18:13
 * @since 1.0
 */
public class XssKillerUtil {
    private static final String[] WHITE_LIST = {"p", "strong", "pre", "code", "span", "blockquote", "em", "a"};
    private static String reg = null;
    private static String legalTags = null;

    static {
        StringBuilder regSb = new StringBuilder("<");
        StringBuilder tagsSb = new StringBuilder();
        for (String s : WHITE_LIST) {
            regSb.append("(?!").append(s).append(" )");
            tagsSb.append("<").append(s).append(">");
        }
        regSb.append("(?!/)[^>]*>");
        reg = regSb.toString();
        legalTags = tagsSb.toString();
    }

    /**
     * xss白名单验证
     *
     * @param xssStr
     * @return
     */
    public static boolean isValid(String xssStr) {
        if (null == xssStr || xssStr.isEmpty()) {
            return true;
        }
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(xssStr);
        while (matcher.find()) {
            String tag = matcher.group();
            if (!legalTags.contains(tag.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    /**
     * xss白名单验证（Jsoup工具，效率较自己实现的那个有些差劲，见com.zyd.blog.util.XssKillerTest.test1()）
     *
     * @param xssStr
     * @return
     */
    public static boolean isValidByJsoup(String xssStr) {
        return Jsoup.isValid(xssStr, custome());
    }

    /**
     * 自定义的白名单
     *
     * @return
     */
    private static Whitelist custome() {
        return Whitelist.none().addTags("p", "strong", "pre", "code", "span", "blockquote", "br").addAttributes("span", "class");
    }

    /**
     * 根据白名单，剔除多余的属性、标签
     *
     * @param xssStr
     * @return
     */
    public static String clean(String xssStr) {
        if (null == xssStr || xssStr.isEmpty()) {
            return "";
        }
        return Jsoup.clean(xssStr, custome());
    }

    public static String escape(String xssStr) {
        if (null == xssStr || xssStr.isEmpty()) {
            return "";
        }

        // TODO ...
        return xssStr;
    }
}
