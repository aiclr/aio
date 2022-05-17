package org.bougainvilleas.spring.ioc.xml.autowire;

/**
 * 自动装配
 * @author renqiankun
 */
public class Emp {
    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                '}';
    }
}
