package com.zyd.blog.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2019/3/21 17:54
 * @since 1.8
 */
@Component
@EnableScheduling
public class SchedulingConfig implements SchedulingConfigurer {

    private AtomicInteger integer = new AtomicInteger(0);

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        // 指定使用自定义的调度器
        scheduledTaskRegistrar.setScheduler(newExecutors());
    }

    /**
     * 实现多线程并行执行定时任务，防止串行带来了性能消耗
     */
    @Bean(destroyMethod = "shutdown")
    private Executor newExecutors() {
        return Executors.newScheduledThreadPool(5, r -> new Thread(r, String.format("OneBlog-Task-%s", integer.incrementAndGet())));
    }
}
