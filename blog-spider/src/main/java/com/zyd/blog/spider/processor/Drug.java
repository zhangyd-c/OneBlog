package com.zyd.blog.spider.processor;

import java.util.List;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/7/24 11:08
 * @since 1.0
 */
public class Drug {
    private String fileName;
    private String shortName;
    private String title;
    private List<DrugItem> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DrugItem> getItems() {
        return items;
    }

    public void setItems(List<DrugItem> items) {
        this.items = items;
    }

    public String getShortName() {
        if (null == this.shortName || this.shortName.isEmpty()) {
            this.shortName = this.getTitle().substring(this.getTitle().indexOf("表") + 1);
            this.shortName = this.shortName.replaceAll("年([0-9]{1,1})月", "年0$1月");
//            this.shortName = this.shortName.replaceAll("[0-9]{4}年", "");
            this.shortName = this.shortName.replaceAll("至([0-9]{1,1})月", "至0$1月");
            this.shortName = this.shortName.replaceAll("月([0-9]{1,1})日", "月0$1日");
            this.shortName = this.shortName.replaceAll("([年月日()（）])", "");
            this.shortName = this.shortName.replaceAll("至", "-");
            this.shortName = this.shortName.replaceAll("签发期：", "");
            this.shortName = this.shortName.replaceAll("- 1", "");
        }
        return shortName;
    }

    public static void main(String[] args) {
        String str = "中检院生物制品批签发信息公示表（签发日期：2017年12月21日至2018年2月2日）- 1";
        Drug drug = new Drug();
        drug.setTitle(str);
        System.out.println(drug.getShortName());
        System.out.println(drug.getFileName());
    }

    public String getFileName() {
        String fileName = this.getTitle().substring(0, this.getTitle().indexOf("表") + 1);
        fileName += this.getTitle().substring(this.getTitle().indexOf("表") + 1)
                .replaceAll("[0-9]{1,2}日至", "-")
                .replaceAll("[0-9]{1,2}日）", "）")
                .replaceAll("签发日期：", "")
                .replaceAll("- 1", "");
        return fileName;
    }

}
