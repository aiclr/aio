package org.bougainvilleas.spring.ioc.xml;

/**
 * 一个员工属于一个部门
 * @author renqiankun
 */
public class Emp {
    private String name;
    private Dept dept;

    public void setName(String name) {
        this.name = name;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    /**
     * 增加get方法，可以注入内部Dept的name属性
     * 目的是获取到Dept 然后在对Dept属性赋值
     * 会覆盖掉Dept原有的值
     */
    public Dept getDept() {
        return dept;
    }

    public void show(){
        System.out.println(dept+"::"+name);
    }
}
