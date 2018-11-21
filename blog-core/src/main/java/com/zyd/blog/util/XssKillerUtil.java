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
