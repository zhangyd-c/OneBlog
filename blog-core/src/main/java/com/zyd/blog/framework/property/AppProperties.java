package com.zyd.blog.framework.property;

import com.zyd.blog.business.consts.DateConst;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Component
@ConfigurationProperties(prefix = "app")
@Data
public class AppProperties {

    /**
     * 是否启用验证码
     */
    public boolean enableKaptcha = false;

    /*
     * 创建网站的时间，用于计算已建站的天数，默认为2018-01-01
     */
    @DateTimeFormat(pattern = DateConst.YYYY_MM_DD_HH_MM_SS_EN)
    private Date buildWebsiteDate = Date.from(LocalDate.of(2018,1,1).atStartOfDay(ZoneId.systemDefault()).toInstant());

}
