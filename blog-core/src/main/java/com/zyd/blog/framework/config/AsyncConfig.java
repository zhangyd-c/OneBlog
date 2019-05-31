package com.zyd.blog.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

/**
 * 异步线程配置
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/28 11:04
 * @since 1.0
 */
@Slf4j
@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

    @Override
    @Bean
    public Executor getAsyncExecutor() {
        return new ContextAwarePoolExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SpringAsyncExceptionHandler();
    }

    class SpringAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            log.error("异步线程发生异常！Method [{}]，Error Message [{}]", method.getName(), throwable.getMessage());
        }
    }

    public class ContextAwarePoolExecutor extends ThreadPoolTaskExecutor {
        @Override
        public <T> Future<T> submit(Callable<T> task) {
            return super.submit(new ContextAwareCallable(task, RequestContextHolder.currentRequestAttributes()));
        }

        @Override
        public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
            return super.submitListenable(new ContextAwareCallable(task, RequestContextHolder.currentRequestAttributes()));
        }
    }

    public class ContextAwareCallable<T> implements Callable<T> {
        private Callable<T> task;
        private RequestAttributes context;

        public ContextAwareCallable(Callable<T> task, RequestAttributes context) {
            this.task = task;
            this.context = context;
        }

        @Override
        public T call() throws Exception {
            if (context != null) {
                RequestContextHolder.setRequestAttributes(context);
            }

            try {
                return task.call();
            } finally {
                RequestContextHolder.resetRequestAttributes();
            }
        }
    }
}
