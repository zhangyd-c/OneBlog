package com.zyd.blog.spider.processor;

import com.github.crab2died.ExcelUtils;
import com.github.crab2died.exceptions.Excel4JException;
import com.github.crab2died.sheet.wrapper.NoTemplateSheetWrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 抓取疫苗信息
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/7/23 10:38
 * @since 1.0
 */
public class DrugSpiderProcessor implements PageProcessor {

    private static List<Drug> drugs = new LinkedList<>();

    public static List<Drug> run() {
        Spider spider = Spider.create(new DrugSpiderProcessor())
                .addUrl(
                        "http://www.nicpbp.org.cn/CL0108/",
                        "http://www.nicpbp.org.cn/CL0873/",
                        "http://www.nicpbp.org.cn/CL0833/",
                        "http://www.nicpbp.org.cn/CL0792/",
                        "http://www.nicpbp.org.cn/CL0737/",
                        "http://www.nicpbp.org.cn/CL0428/"
                )
                .thread(5);
        // 启动爬虫
        spider.run();
        export();
        return drugs;
    }

    private static void export() {
        Long start = System.currentTimeMillis();
        List<NoTemplateSheetWrapper> sheets = null;
        Map<String, List<Drug>> map = new HashMap<>();
        for (Drug drug : drugs) {
            List<Drug> list = map.get(drug.getFileName());
            if (null == list) {
                list = new LinkedList<>();
            }
            list.add(drug);
            map.put(drug.getFileName(), list);
        }
        for (Map.Entry<String, List<Drug>> stringListEntry : map.entrySet()) {
            sheets = new LinkedList<>();
            String fileName = stringListEntry.getKey();
            List<Drug> drugs = stringListEntry.getValue();
            for (Drug drug : drugs) {
                sheets.add(new NoTemplateSheetWrapper(drug.getItems(), DrugItem.class, true, drug.getShortName()));
            }
            try {
                String filePath = "D://excel/" + fileName + ".xlsx";
                File file = new File(filePath);
                if (!file.exists()) {
                    file.getParentFile().mkdirs();
                }
                ExcelUtils.getInstance().noTemplateSheet2Excel(sheets, filePath);
            } catch (Excel4JException | IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println((System.currentTimeMillis() - start) + "ms");

    }

    private static String html2Text(String content) {
        if (StringUtils.isEmpty(content)) {
            return null;
        }
        String regexHtml = "<[^>]+>";
        content = content.replaceAll(regexHtml, "").replaceAll(" ", "");
        content = content.replaceAll("&quot;", "\"")
                .replaceAll("&nbsp;", "")
                .replaceAll("\n", " ")
                .replaceAll("&amp;", "&")
                .replaceAll("&lt;", "<")
                .replaceAll("&#39;", "\'")
                .replaceAll("&gt;", ">")
                .replaceAll("[ \\f\\t\\v]{2,}", "\t");

        String regEx = "<.+?>";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(content);
        content = matcher.replaceAll("");
        return content.trim();
    }

    @Override
    public void process(Page page) {
        String source = page.getRequest().getUrl();
        System.out.println("source + " + source);
        Html pageHtml = page.getHtml();
        List<Selectable> trs = pageHtml.xpath("//td[@class='articlecontent1']/table").xpath("//tr").nodes();
        Drug drug = new Drug();
        drug.setTitle(html2Text(pageHtml.xpath("//td[@class='articletitle1']").get()));
        if (CollectionUtils.isNotEmpty(trs)) {
            List<DrugItem> items = new LinkedList<>();
            DrugItem item = null;
            boolean firstRow = true;
            for (Selectable tr : trs) {
                if (firstRow) {
                    firstRow = false;
                    continue;
                }
                try {
                    List<Selectable> tds = tr.xpath("//td").nodes();
                    if (CollectionUtils.isNotEmpty(tds)) {
                        if (tds.size() <= 1) {
                            continue;
                        }
                        item = new DrugItem();
                        if (tds.size() == 8) {
                            // http://www.nicpbp.org.cn/CL0737/5324.html
                            setItemParams(item, tds, new int[]{0, 1, 2, 3, 4, 5, 6, 7});
                        } else if (tds.size() == 9) {
                            // http://www.nifdc.org.cn/directory/web/WS02/CL0833/8512.html
                            setItemParams(item, tds, new int[]{0, 1, 2, 3, 4, 6, 7, 8});
                        } else if (tds.size() == 10) {
                            // http://www.nicpbp.org.cn/CL0792/7776.html
                            setItemParams(item, tds, new int[]{0, 1, 2, 3, 4, 7, 8, 9});
                        } else if (tds.size() == 12) {
                            // http://www.nicpbp.org.cn/CL0903/10477.html
                            setItemParams(item, tds, new int[]{0, 1, 2, 3, 4, 5, 6, 11});
                        }

                        // 过滤掉某些页面的表格里的数据统计部分
                        if (StringUtils.isEmpty(item.getCompany()) || "――".equals(item.getCompany()) || StringUtils.isEmpty(item.getExpire()) || "――".equals(item.getExpire())) {
                            continue;
                        }
                        try {
                            String sqlFormat = "INSERT INTO `wx_drug_item` (`product_name`,`specification`,`lot_number`,`lot_size`,`expire`,`company`,`result`, `source_url`) " +
                                    "VALUES('%s','%s','%s','%s','%s','%s','%s', '%s');";
                            MysqlUtil.initParams();
                            MysqlUtil.getStatement(String.format(sqlFormat, item.getName(), item.getGuige(), item.getPihao(), item.getPiliang(), item.getExpire(), item.getCompany(), item.getResult(), source)).execute();
                            MysqlUtil.destroy();
                        } catch (ClassNotFoundException | SQLException e) {
                            e.printStackTrace();
                        }
                        items.add(item);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            drug.setItems(items);
            drugs.add(drug);
        }
        Selectable links = page.getHtml().links();
        // 正常页面
        List<String> list = links.regex(".*www\\.nicpbp\\.org\\.cn/CL[0-9]{3,5}/[0-9]{1,10}\\.html").all();
        if (CollectionUtils.isEmpty(list)) {
            // http://www.nifdc.org.cn/directory/web/WS02/CL0833/8898.html
            // 特殊页面
            list = links.regex(".*www\\.nifdc\\.org\\.cn:/directory/web/WS02/CL[0-9]{3,5}/[0-9]{1,10}\\.html").all();
        }
        if (CollectionUtils.isEmpty(list)) {
            return;
        }
        Collections.reverse(list);
        page.addTargetRequests(list);
    }

    private void setItemParams(DrugItem item, List<Selectable> tds, int[] indexs) {
        if (indexs.length < 8) {
            return;
        }
        item.setNum(html2Text(tds.get(indexs[0]).get()));
        item.setName(html2Text(tds.get(indexs[1]).get()));
        item.setGuige(html2Text(tds.get(indexs[2]).get()));
        item.setPihao(html2Text(tds.get(indexs[3]).get()));
        item.setPiliang(html2Text(tds.get(indexs[4]).get()));
        item.setExpire(html2Text(tds.get(indexs[5]).get()));
        item.setCompany(html2Text(tds.get(indexs[6]).get()));
        item.setResult(html2Text(tds.get(indexs[7]).get()));
    }

    @Override
    public Site getSite() {
        return Site.me()
                .setCharset("GB2312")
                .setSleepTime(2000)
                .setRetryTimes(2)
                .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.99 Safari/537.36");

    }
}
