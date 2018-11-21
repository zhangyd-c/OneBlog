package com.zyd.blog.framework.runner;

import com.zyd.blog.business.consts.DateConst;
import com.zyd.blog.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 程序启动后通过ApplicationRunner处理一些事务
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/6/6 16:07
 * @since 1.0
 */
@Slf4j
@Component
public class BlogApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments applicationArguments) {
        log.info("博客部署完成，当前时间：" + DateUtil.date2Str(new Date(), DateConst.YYYY_MM_DD_HH_MM_SS_EN));
    }
}
