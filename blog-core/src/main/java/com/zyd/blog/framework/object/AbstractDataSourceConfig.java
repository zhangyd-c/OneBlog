package com.zyd.blog.framework.object;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 添加数据源的抽象配置，详细实现根据业务定制。
 * Created by yadong.zhang on cn.com.innodev.health.framework.config
 * 必备的注解参数<br>
 * <code>@Configuration</code><br>
 * <code>@MapperScan(basePackages = "xxMapper.java所在的包路径", sqlSessionTemplateRef  = "同下方transactionManagerBeanName")</code><br>
 *
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @website https://www.zhyd.me
 * @version 1.0
 * @date 2018/4/16 16:26
 * @since 1.0
 */
public abstract class AbstractDataSourceConfig {

    /**
     * 声明注入数据源<br>
     * 必备的注解参数<br>
     * <code>@Bean(name = "dataSourceBeanName") - 识别bean的唯一标识，应全局唯一</code><br>
     * 非必须,可以通过@Value自己声明数据源参数，然后使用第三方数据源（druid）工具注入<br>
     * <code>@ConfigurationProperties(prefix = "xx.xx.xx")</code><br>
     *
     * @return
     */
    public abstract DataSource injectDataSource();

    /**
     * 声明注入SqlSessionFactory<br>
     * 必备的注解参数<br>
     * <code>@Bean(name = "sqlSessionFactoryBeanName") - 识别bean的唯一标识，应全局唯一</code>
     * <br>
     * DataSource参数一定要通过下面的注解声明<br>
     * <code>@Qualifier("dataSourceBeanName")</code><br>
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    public abstract SqlSessionFactory injectSqlSessionFactory(DataSource dataSource) throws Exception;

    /**
     * 声明注入事务管理DataSourceTransactionManager<br>
     * 必备的注解参数<br>
     * <code>@Bean(name = "transactionManagerBeanName") - 识别bean的唯一标识，应全局唯一</code>
     * <br>
     * DataSource参数一定要通过下面的注解声明<br>
     * <code>@Qualifier("sqlSessionFactoryBeanName")</code><br>
     *
     * @param dataSource
     * @return
     */
    public abstract DataSourceTransactionManager injectTransactionManager(DataSource dataSource);

    /**
     * 声明注入SqlSessionTemplate<br>
     * 必备的注解参数<br>
     * <code>@Bean(name = "sqlSessionTemplateBeanName") - 识别bean的唯一标识，应全局唯一</code>
     * <br>
     * SqlSessionFactory参数一定要通过下面的注解声明<br>
     * <code>@Qualifier("sqlSessionFactoryBeanName")</code><br>
     *
     * @param sqlSessionFactory
     * @return
     * @throws Exception
     */
    public abstract SqlSessionTemplate injectSqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception;
}
