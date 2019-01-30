/**
 * Copyright © 2017 Innodev. All rights reserved.
 * Mysql数据库操作工具类
 *
 * @Package: uk.co.quidos.framework.utils
 * @author: zhyd
 * @date: 2017年5月12日 上午11:37:31
 * @version: V1.0
 */
package com.zyd.blog.spider.processor;


import org.apache.commons.lang3.StringUtils;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Mysql数据库操作工具类<br>
 *
 * @ClassName: MysqlUtil
 * @author: zhyd
 * @date: 2017年5月12日 上午11:37:31
 */
public class MysqlUtil {

    private static final String KEY_DRIVER_CLASS_NAME = "driverClassName";
    private static final String KEY_URL = "url";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_DATABASE_NAME = "databaseName";
    private static final String KEY_IS_LOAD_PARAMS = "isLoadParams";

    // 链接数据库的参数信息
    private static ThreadLocal<Map<String, String>> initParamMap = ThreadLocal.withInitial(() -> {
        Map<String, String> map = new ConcurrentHashMap<String, String>();
        map.put(KEY_IS_LOAD_PARAMS, "false");
        return map;
    });

    // 数据库操作对象
    private static ThreadLocal<Connection> connection = new ThreadLocal<Connection>();
    private static ThreadLocal<Statement> statement = new ThreadLocal<Statement>();
    private static ThreadLocal<PreparedStatement> preparedStatement = new ThreadLocal<PreparedStatement>();

    /**
     * 通过指定的配置文件 初始化数据库链接参数
     *
     * @return void
     */
    public static void initParams() {
        if (getValue(KEY_IS_LOAD_PARAMS).equalsIgnoreCase("true")) {
            return;
        }
        Map<String, String> map = new ConcurrentHashMap<>(10);
        map.put(KEY_DRIVER_CLASS_NAME, "com.mysql.jdbc.Driver");
        map.put(KEY_URL, "jdbc:mysql://localhost:3306/blog?useUnicode=true&characterEncoding=utf-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true");
        map.put(KEY_USERNAME, "root");
        map.put(KEY_PASSWORD, "root");
        map.put(KEY_IS_LOAD_PARAMS, "true");
        initParamMap.set(map);
    }

    /**
     * 获取数据库链接
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return Connection
     */
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        if (connection.get() == null) {
            Class.forName(getValue(KEY_DRIVER_CLASS_NAME));
            connection.set(
                    DriverManager.getConnection(getValue(KEY_URL), getValue(KEY_USERNAME), getValue(KEY_PASSWORD)));
        }
        return connection.get();
    }

    /**
     * 获取Statement
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return Statement
     */
    public static Statement getStatement() throws ClassNotFoundException, SQLException {
        if (statement.get() == null) {
            statement.set(getConnection().createStatement());
        }
        return statement.get();
    }

    /**
     * 获取PreparedStatement
     *
     * @param sql
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return PreparedStatement
     */
    public static PreparedStatement getStatement(String sql) throws ClassNotFoundException, SQLException {
        if (preparedStatement.get() == null) {
            preparedStatement.set(getConnection().prepareStatement(sql));
        }
        return preparedStatement.get();
    }

    /**
     * 获取数据库名
     *
     * @return String
     */
    public static String getDatabaseName() {
        if (StringUtils.isEmpty(getValue(KEY_DATABASE_NAME))) {
            int charIndex = getValue(KEY_URL).indexOf("?");
            if (charIndex != -1) {
                return getValue(KEY_URL).substring(getValue(KEY_URL).lastIndexOf("/") + 1, charIndex);
            }
        }
        return getValue(KEY_DATABASE_NAME);
    }

    /**
     * 获取map值
     *
     * @param key
     * @return String
     */
    private static String getValue(String key) {
        Map<String, String> map = initParamMap.get();
        if (map != null) {
            return map.get(key);
        }
        return "";
    }

    /**
     * 销毁资源
     *
     * @return boolean
     */
    public static boolean destroy() {
        // 关闭资源
        close(connection.get(), statement.get(), preparedStatement.get());
        // 删除当前线程局部变量的值
        connection.remove();
        statement.remove();
        preparedStatement.remove();
        initParamMap.remove();
        return true;
    }

    /**
     * 关闭资源
     *
     * @param closeables
     *            待关闭的资源对象
     * @return void
     */
    public static void close(AutoCloseable... closeables) {
        if (closeables != null && closeables.length > 0) {
            for (AutoCloseable closeable : closeables) {
                if (closeable != null) {
                    try {
                        closeable.close();
                        closeable = null;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
