package com.zyd.blog.business.service;

import me.zhyd.hunter.config.HunterConfig;

import java.io.PrintWriter;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/8/21 15:35
 * @since 1.8
 */
public interface RemoverService {

    void run(Long typeId, HunterConfig config, PrintWriter writer);

    void stop();

    void crawlSingle(Long typeId, String[] url, boolean convertImg, PrintWriter writer);
}
