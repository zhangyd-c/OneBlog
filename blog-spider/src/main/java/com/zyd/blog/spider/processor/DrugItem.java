package com.zyd.blog.spider.processor;

import com.github.crab2died.annotation.ExcelField;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/7/24 11:08
 * @since 1.0
 */
public class DrugItem {
    @ExcelField(title = "序号", order = 1)
    private String num;
    @ExcelField(title = "制品名称", order = 2)
    private String name;
    @ExcelField(title = "规格", order = 3)
    private String guige;
    @ExcelField(title = "批号", order = 4)
    private String pihao;
    @ExcelField(title = "批量/进口量", order = 5)
    private String piliang;
    @ExcelField(title = "有效期至", order = 6)
    private String expire;
    @ExcelField(title = "生产企业", order = 7)
    private String company;
    @ExcelField(title = "签发结论", order = 8)
    private String result;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuige() {
        return guige;
    }

    public void setGuige(String guige) {
        this.guige = guige;
    }

    public String getPihao() {
        return pihao;
    }

    public void setPihao(String pihao) {
        this.pihao = pihao;
    }

    public String getPiliang() {
        return piliang;
    }

    public void setPiliang(String piliang) {
        this.piliang = piliang;
    }

    public String getExpire() {
        return expire;
    }

    public void setExpire(String expire) {
        this.expire = expire;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
