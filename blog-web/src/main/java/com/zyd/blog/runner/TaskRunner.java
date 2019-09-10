package com.zyd.blog.runner;

import cn.hutool.core.thread.ThreadFactoryBuilder;
import com.zyd.blog.core.schedule.ArticleLookTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 执行保存文章浏览记录任务
 *
 * @author kongchong
 * date: 2019-07-10 11:17
 */
@Component
public class TaskRunner implements ApplicationRunner {

    @Autowired
    private ArticleLookTask articleLookTask;

    @Override
    public void run(ApplicationArguments args) {
        ThreadFactory articleLookThreadFactory = new ThreadFactoryBuilder().setNamePrefix("BLOG-ARTICLE_LOOK-").build();

        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                3650L, TimeUnit.DAYS,
                new LinkedBlockingQueue<Runnable>(1024),
                articleLookThreadFactory, new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(()-> articleLookTask.save());
        singleThreadPool.shutdown();
    }
}
