package org.bougainvilleas.spring.ioc.xml.collection;

/**
 * 集合注入
 * @author renqiankun
 */
public class Course {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                '}';
    }
}
