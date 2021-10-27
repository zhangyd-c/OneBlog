package com.zyd.blog.runner;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.xkcoding.http.HttpUtil;
import com.xkcoding.http.support.hutool.HutoolImpl;
import com.zyd.blog.core.schedule.ArticleLookTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 *
 * @author kongchong
 * date: 2019-07-10 11:17
 */
@Slf4j
@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    private ArticleLookTask articleLookTask;

    @Override
    public void run(ApplicationArguments args) {

        // 重置 simple-http 的使用策略
        HttpUtil.setHttp(new HutoolImpl());

        // 执行保存文章浏览记录任务
        ThreadFactory articleLookThreadFactory = new ThreadFactoryBuilder().setNamePrefix("BLOG-ARTICLE_LOOK-").build();

        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                3650L, TimeUnit.DAYS,
                new LinkedBlockingQueue<Runnable>(1024),
                articleLookThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(() -> articleLookTask.save());
        singleThreadPool.shutdown();
    }
}
