package org.bougainvilleas.spring.ioc.xml.autowire;

/**
 * 自动装配
 * @author renqiankun
 */
public class Dept {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "name='" + name + '\'' +
                '}';
    }
}
