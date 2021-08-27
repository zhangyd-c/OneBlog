package com.zyd.blog.business.util;

import com.zyd.blog.business.consts.HttpConsts;
import com.zyd.blog.util.HtmlUtil;
import com.zyd.blog.util.RestClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 友情链接工具类
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://docs.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
public class LinksUtil {
    /**
     * 单位b，1kb = 1024b
     */
    private static final int DEFAULT_FACICON_MAX_SIZE = 1024 * 5;
    private static final int DEFAULT_FACICON_MIN_SIZE = 1;

    /**
     * 获取网站favicon图标的正则表达式
     */
    private static final Pattern[] ICON_PATTERNS = new Pattern[]{
            Pattern.compile("rel=[\"']icon[\"'][^\r\n>]+?((?<=href=[\"']).+?(?=[\"']))"),
            Pattern.compile("((?<=href=[\"']).+?(?=[\"']))[^\r\n<]+?rel=[\"']icon[\"']")};

    /**
     * 获取网站的favicon图标<br>
     * https站点可能存在问题
     *
     * @param url 网站地址
     * @return favicon地址
     * @deprecated 当前版本为前台用户手动录入favicon，可考虑后台应用使用该接口
     */
    @Deprecated
    public static String getFavicon(String url) {
        if (!url.startsWith(HttpConsts.HTTP_PROROCOL) && !url.startsWith(HttpConsts.HTTPS_PROROCOL)) {
            url = HttpConsts.HTTP_PROROCOL + url;
        }
        String html = RestClientUtil.get(url);
        for (Pattern iconPattern : ICON_PATTERNS) {
            Matcher matcher = iconPattern.matcher(html);
            if (matcher.find()) {
                return matcher.group(1);
            }
        }
        return null;
    }

    /**
     * 检查favicon的有效性
     *
     * @param faviocnUrl favicon地址
     * @return true favicon大小合适，false favicon过大或者过小
     */
    public static boolean checkFavicon(String faviocnUrl) {
        int faviconLength = getFaviconSize(faviocnUrl);
        return faviconLength >= DEFAULT_FACICON_MIN_SIZE && faviconLength < DEFAULT_FACICON_MAX_SIZE;
    }

    /**
     * 获取网站的favicon图标大小
     *
     * @param faviocnUrl favicon地址
     * @return favicon图标大小
     */
    private static int getFaviconSize(String faviocnUrl) {
        int contentLength = 0;
        try {
            final URL url = new URL(faviocnUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(HttpMethod.GET.name());
            connection.setDoOutput(true);
            // 必须设置false，否则会自动redirect到Location的地址
            connection.setInstanceFollowRedirects(false);
            contentLength = connection.getContentLength();
            log.debug("Favicon size : {}", contentLength);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            log.error("请求地址不对", e);
        } catch (ProtocolException e) {
            e.printStackTrace();
            log.error("请求地址协议异常", e);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("数据传输异常", e);
        }
        return contentLength;
    }

    /**
     * 爬取url所在的页面，查找是否添加了本站友情链接
     *
     * @param url        目标站地址
     * @param domainName 本站地址
     * @return true 已链接本站，false 未链接
     */
    public static boolean hasLinkByHtml(String url, String domainName) {
        if (!url.startsWith(HttpConsts.HTTP_PROROCOL) && !url.startsWith(HttpConsts.HTTPS_PROROCOL)) {
            url = HttpConsts.HTTP_PROROCOL + url;
        }
        String html = RestClientUtil.get(url);
        return !StringUtils.isEmpty(html) && html.contains(domainName);
    }

    /**
     * 通过Chinaz接口
     * 查找是否添加了本站友情链接
     *
     * @param url        目标站地址
     * @param domainName 本站地址
     * @return true 已链接本站，false 未链接
     */
    public static boolean hasLinkByChinaz(String url, String domainName) {
        if (url.startsWith(HttpConsts.HTTP_PROROCOL) || url.startsWith(HttpConsts.HTTPS_PROROCOL)) {
            url = url.replace(HttpConsts.HTTP_PROROCOL, "");
            url = url.replace(HttpConsts.HTTPS_PROROCOL, "");
        }
        try {
            Document htmlDocument = Jsoup.parse(RestClientUtil.get("http://link.chinaz.com/" + url));
            Element ulElement = HtmlUtil.getElementById(htmlDocument, "ulLink");
            int maxRequestCount = 2;
            while (ulElement == null) {
                if (maxRequestCount == 0) {
                    return false;
                }
                System.err.println("没有获取到element.还剩" + maxRequestCount + "次获取机会.");
                htmlDocument = Jsoup.parse(RestClientUtil.get("http://link.chinaz.com/" + url));
                ulElement = HtmlUtil.getElementById(htmlDocument, "ulLink");
                maxRequestCount--;
            }

            Elements liElements = ulElement.getElementsByTag("li");
            if (liElements == null || liElements.size() <= 0) {
                return false;
            }
            for (int i = 1; i < liElements.size(); i++) {
                Element liElement = liElements.get(i);
                if (liElement == null) {
                    continue;
                }
                String text = liElement.getElementsByClass("tl").get(0).getElementsByTag("span").get(1).getElementsByTag("a").get(0).text();
                if (text.startsWith(domainName) || text.startsWith("www." + domainName)
                        || text.startsWith(HttpConsts.HTTP_PROROCOL + domainName) || text.startsWith(HttpConsts.HTTP_PROROCOL + "www." + domainName)
                        || text.startsWith(HttpConsts.HTTPS_PROROCOL + domainName) || text.startsWith(HttpConsts.HTTPS_PROROCOL + "www." + domainName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.error("访问Chinaz网站失败！", e);
        }
        return false;
    }
}
