package com.zyd.blog.codegen;

import me.zhyd.houtu.Generator;
import me.zhyd.houtu.config.Config;

import java.io.File;

public class BlogCodegenApplication {

    public static void main(String[] args) {
        Generator g = new Generator(Config.getInstance()
                .setDriver("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://127.0.0.1:3306/dblog?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&useSSL=false&allowPublicKeyRetrieval=true&useLegacyDatetimeCode=false")
                .setUsername("root")
                .setPassword("root")
                .setPrimaryKeyType("String")
                .setBasePackage("com.zyd.blog")
                .setBeansPackage("com.zyd.blog.persistence.beans")
                .setMapperPackage("com.zyd.blog.persistence.mapper")
                .setClassPrefix("")
                .setClearClassPrefix("")
                .setOutRootDir(System.getProperty("user.dir") + File.separator + "blog-codegen" + File.separator + "generator-output"));

        //删除生成器的输出目录
        g.deleteOutRootDir();

        // 打印所有表
        g.printAllTableInfo();

        // 生成单个表的Java文件
//        g.generate("log");
        // 生成多个表的Java文件
        g.generate("biz_ad");
        // 生成所有表的Java文件
//        g.generate();
        // 生成所有表的Java文件
//        g.generateAll();
        // 生成所有表的Java文件
//        g.generateByPrefix("customer");

        System.out.println("文件已输出到：" + System.getProperty("user.dir") + File.separator + "blog-codegen" + File.separator + "generator-output");
    }

}
