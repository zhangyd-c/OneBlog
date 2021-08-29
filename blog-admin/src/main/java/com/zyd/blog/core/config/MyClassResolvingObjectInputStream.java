package com.zyd.blog.core.config;

import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.util.UnknownClassException;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

public class MyClassResolvingObjectInputStream extends ObjectInputStream {
    public MyClassResolvingObjectInputStream(InputStream inputStream) throws IOException {
        super(inputStream);
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass osc) throws IOException, ClassNotFoundException {
        try {
            String s = osc.getName();
            // 干掉常见的gadget，为了避免 [ ; 符号，必须使用contains方法
            // 简单的使用 s.equals 可能导致fastjson 以前出现的黑名单逃逸问题
            if (s.contains("java.util.PriorityQueue") || s.contains("xsltc.trax.TemplatesImpl")) {
                throw new ClassNotFoundException("Unable to load Dangerous ObjectStreamClass [" + osc + "]");
            }
            if (s.contains("org.apache.")) {
                // 直接干掉了 org.apache ，但是要保留shiro自己
                if (s.startsWith("org.apache.shiro.subject.")) {
                    return ClassUtils.forName(s);
                }
                throw new ClassNotFoundException("Unable to load Dangerous ObjectStreamClass [" + osc + "]");
            }
            // 使用白名单保证业务的正常开展
            if (s.startsWith("java.lang") || s.startsWith("java.util")) {
                return ClassUtils.forName(s);
            } else {
                throw new ClassNotFoundException("Unable to load Dangerous ObjectStreamClass [" + osc + "]");
            }
        } catch (UnknownClassException var3) {
            throw new ClassNotFoundException("Unable to load ObjectStreamClass [" + osc + "]: ", var3);
        }
    }
}
