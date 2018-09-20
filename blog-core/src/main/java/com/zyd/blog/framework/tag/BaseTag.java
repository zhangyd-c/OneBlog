package com.zyd.blog.framework.tag;

import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 所有自定义标签的父类，负责调用具体的子类方法
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @website https://www.zhyd.me
 * @date 2018/9/18 16:19
 * @since 1.8
 */
@Slf4j
public abstract class BaseTag implements TemplateDirectiveModel {

    private static final ConcurrentHashMap<String, Class> classBucket = new ConcurrentHashMap<>();
    private String clazzPath = null;

    public BaseTag(String targetClassPath) {
        clazzPath = targetClassPath;
        if (classBucket.get(clazzPath) == null) {
            try {
                Class clazz = Class.forName(clazzPath);
                classBucket.put(clazzPath, clazz);
            } catch (ClassNotFoundException e) {
                log.error("无法从[{}]获取对应的class", clazzPath, e);
            }
        }
    }

    private String getMethod(Map params) {
        return this.getParam(params, "method");
    }

    protected int getPageSize(Map params) {
        int pageSize = 10;
        String pageSizeStr = this.getParam(params, "pageSize");
        if (!StringUtils.isEmpty(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        }
        return pageSize;
    }

    private void verifyParameters(Map params) throws TemplateModelException {
        String permission = this.getMethod(params);
        if (permission == null || permission.length() == 0) {
            throw new TemplateModelException("The 'name' tag attribute must be set.");
        }
    }

    String getParam(Map params, String paramName) {
        Object value = params.get(paramName);
        return value instanceof SimpleScalar ? ((SimpleScalar) value).getAsString() : null;
    }

    private DefaultObjectWrapper getBuilder() {
        return new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25).build();
    }

    private TemplateModel getModel(Object o) throws TemplateModelException {
        return this.getBuilder().wrap(o);
    }

    @Override
    public void execute(Environment environment, Map map, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        this.verifyParameters(map);
        String funName = getMethod(map);
        Method method = null;
        Class clazz = classBucket.get(clazzPath);
        try {
            if (clazz != null && (method = clazz.getDeclaredMethod(funName, Map.class)) != null) {
                // 核心处理，调用子类的具体方法，获取返回值
                Object res = method.invoke(this, map);
                environment.setVariable(funName, getModel(res));
            }
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            log.error("无法获取[{}]的方法，或者调用[{}]方法发生异常", clazzPath, method, e);
        }
        templateDirectiveBody.render(environment.getOut());
    }

}
