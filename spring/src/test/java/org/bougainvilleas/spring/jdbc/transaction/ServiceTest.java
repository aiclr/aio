package org.bougainvilleas.spring.jdbc.transaction;

import org.bougainvilleas.spring.jdbc.service.transaction.ServiceImpl;
import org.bougainvilleas.spring.jdbc.service.transaction.annotation.ServiceImpl3;
import org.bougainvilleas.spring.jdbc.service.transaction.annotation.TxConfig;
import org.bougainvilleas.spring.jdbc.service.transaction.xml.ServiceImpl2;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.annotation.Rollback;

import javax.sql.DataSource;

/**
 * 事务
 * @author renqiankun
 */
@Disabled
@Rollback
class ServiceTest {

    /**
     * jdbc/dataSource.xml 已经配置了 事务管理器 datasourceTransactionManager
     * 需要注释掉
     * {@link TxConfig#getDataSourceTransactionManager(DataSource)}
     * 的 @Bean
     */
    @Test
    @Disabled
    @DisplayName("需要注释掉TxConfig.getDataSourceTransactionManager()的 @Bean")
    void age(){
        ApplicationContext context=new ClassPathXmlApplicationContext("jdbc/dataSource.xml");
        ServiceImpl serviceImpl = context.getBean("serviceImpl", ServiceImpl.class);
        serviceImpl.age(1,2,1);
    }

    /**
     *
     * ServiceImpl2 全部基于 xml 注入
     * 与 {@link TxConfig#getDataSourceTransactionManager(DataSource)} 不冲突
     */
    @Test
    @DisplayName("全部基于 xml 注入")
    void ageXml(){
        ApplicationContext context=new ClassPathXmlApplicationContext("jdbc/xml/dataSource.xml");
        ServiceImpl2 serviceImpl = context.getBean("serviceImpl", ServiceImpl2.class);
        serviceImpl.age(1,2,1);
    }


    /**
     * 需要开启 TxConfig.getDataSourceTransactionManager()的 @Bean
     * {@link TxConfig#getDataSourceTransactionManager(DataSource)}
     */
    @Test
    @DisplayName("全部基于 注解")
    void ageAnnotation(){
        ApplicationContext context=new AnnotationConfigApplicationContext(TxConfig.class);
        ServiceImpl3 serviceImpl = context.getBean("serviceImpl3", ServiceImpl3.class);
        serviceImpl.age(1,2,1);
    }
}
