package org.bougainvilleas.spring.ioc.xml.factorybean;


import org.bougainvilleas.spring.ioc.xml.collection.Course;
import org.springframework.beans.factory.FactoryBean;

/**
 * 工厂bean
 * @author renqiankun
 */
public class MyBean implements FactoryBean<Course>
{

    @Override
    public Course getObject() {
        Course course = new Course();
        course.setName("工厂bean设置name");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return Course.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
