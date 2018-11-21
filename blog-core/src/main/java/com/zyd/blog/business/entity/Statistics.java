package com.zyd.blog.business.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.zyd.blog.persistence.beans.BizStatistics;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public class Statistics {

    private BizStatistics bizStatistics;

    public Statistics(BizStatistics bizStatistics) {
        this.bizStatistics = bizStatistics;
    }

    public Statistics() {
    }

    @JsonIgnore
    public BizStatistics getBizStatistics() {
        return bizStatistics;
    }

    public String getName() {
        return this.bizStatistics.getName();
    }

    public void setName(String name) {
        this.bizStatistics.setName(name);
    }

    public Integer getValue() {
        return this.bizStatistics.getValue();
    }

    public void setValue(Integer value) {
        this.bizStatistics.setValue(value);
    }
}
