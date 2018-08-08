package com.zyd.blog.util;

import org.junit.Test;

public class XssKillerTest {

//    String xssStr = "<p><strong>你好*</strong>asdasd*<code>asdasd</code></p>\n" +
//            "<pre><code><span class=\"hljs-tag\" style=\"width:100%\">&lt;<span class=\"hljs-name\">script</span>&gt;</span><span class=\"javascript\">alert(<span class=\"hljs-string\">\"xss killer.\"</span>);</span><span class=\"hljs-tag\">&lt;/<span class=\"hljs-name\">script</span>&gt;</span>\n" +
//            "</code></pre><p>\n" +
//            "<script>alert(&quot;xss killer.&quot;);</script></p>\n" +
//            "<blockquote>\n" +
//            "<p>asdsdsad</p>\n" +
//            "</blockquote><img src=\"https://www.baiduu.com\" >";
    String xssStr = "<p></p></table></p>";

    @Test
    public void main() {
        System.out.println(XssKillerUtil.isValid(xssStr));
        System.out.println("********************************************************************");

        System.out.println(XssKillerUtil.isValidByJsoup(xssStr));
        System.out.println("********************************************************************");

        System.out.println(XssKillerUtil.clean(xssStr));
        System.out.println("********************************************************************");
    }

    /**
     * 10000次：
     * validateByReg : 128ms
     * Jsoup.isValid : 1061ms
     * Jsoup.clean : 674ms
     * <p>
     * 100000次：
     * validateByReg : 629ms
     * Jsoup.isValid : 2709ms
     * Jsoup.clean : 2152ms
     */
    @Test
    public void test1() {
        int loopCount = 10000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < loopCount; i++) {
            XssKillerUtil.isValid(xssStr);
        }
        long end = System.currentTimeMillis();
        System.out.println("validateByReg : " + (end - start) + "ms");

        for (int i = 0; i < loopCount; i++) {
            XssKillerUtil.isValidByJsoup(xssStr);
        }
        long end1 = System.currentTimeMillis();
        System.out.println("Jsoup.isValid : " + (end1 - end) + "ms");


        for (int i = 0; i < loopCount; i++) {
            XssKillerUtil.clean(xssStr);
        }
        long end2 = System.currentTimeMillis();
        System.out.println("Jsoup.clean : " + (end2 - end1) + "ms");
    }
}
