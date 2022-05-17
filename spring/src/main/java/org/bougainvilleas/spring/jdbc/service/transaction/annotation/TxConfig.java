package org.bougainvilleas.spring.jdbc.service.transaction.annotation;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * 完全注解模式
 *
 * @author renqiankun
 */
@Configuration
@ComponentScan(basePackages = {"org.bougainvilleas.spring.jdbc"})
@EnableTransactionManagement//开启事务
public class TxConfig {

    /**
     * 创建数据库连接池
     * 会注入到IOC容器中
     */
    @Bean
    public DruidDataSource getDruidDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/spring5");
        dataSource.setUsername("caddy");
        dataSource.setPassword("caddy@415");
        return dataSource;
    }

    /**
     * 创建JdbcTemplate
     *
     * @param dataSource IOC已经有DruidDataSource对象,
     *                   此处直接从IOC容器根据类型注入DataSource即可
     */
    @Bean
    public JdbcTemplate getJdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource);
        return jdbcTemplate;
    }

    /**
     * 创建事务管理器
     * @param dataSource
     * @return
     */
    @Bean
    public DataSourceTransactionManager getDataSourceTransactionManager(DataSource dataSource){
        DataSourceTransactionManager dataSourceTransactionManager=new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }


}
