/**
 * MIT License
 * Copyright (c) 2018 yadong.zhang
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.zyd.blog.business.aspect;

import com.zyd.blog.business.annotation.RedisCache;
import com.zyd.blog.framework.property.AppProperties;
import com.zyd.blog.util.CacheKeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Redis业务层数据缓存
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/4/16 16:26
 * @since 1.0
 */
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AppProperties propertiesConfig;

    @Pointcut(value = "@annotation(com.zyd.blog.business.annotation.RedisCache)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object handle(ProceedingJoinPoint point) throws Throwable {
        // 获取拦截的方法名
        Signature sig = point.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Object target = point.getTarget();
        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
        if (!currentMethod.isAnnotationPresent(RedisCache.class)) {
            throw new RuntimeException("未指定RedisChache注解！");
        }
        StringBuilder key = new StringBuilder(point.getTarget().getClass().getName());
        // 获取操作名称
        RedisCache cache = currentMethod.getAnnotation(RedisCache.class);
        if (cache.flush()) {
            log.info("{}*-清空缓存", key);
            Set<String> keys = redisTemplate.keys(key.toString() + "*");
            redisTemplate.delete(keys);
            log.info("Clear all the cached query result from redis");
            return point.proceed();
        }
        key.append(".").append(currentMethod.getName());
        key.append(CacheKeyUtil.getMethodParamsKey(point.getArgs())).append(cache.key());
        String realKey = key.toString();
        // 缓存存在
        boolean hasKey = redisTemplate.hasKey(realKey);
        if (hasKey) {
            try {
                log.info("{}从缓存中获取数据", realKey);
                return redisTemplate.opsForValue().get(key);
            } catch (Exception e) {
                log.error("从缓存中获取数据失败！", e);
            }
        }
        // 先执行业务
        Object result = point.proceed();
        // 向Redis中添加数据，有效时间是30天
        redisTemplate.opsForValue().set(realKey, result, cache.expire(), cache.unit());
        log.info("Put query result to redis");
        log.info("{}从数据库中获取数据", realKey);
        return result;
    }
}
