package org.bougainvilleas.spring.ioc.xml;

/**
 * 部门
 * 注入内部bean
 *
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
