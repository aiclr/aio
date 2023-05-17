package org.bougainvilleas.ilj.designpattern.behavior.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 具体聚合持有对象集合,提供方法,返回一个可以正确遍历集合的迭代器,
 */
public class CollegeInfo implements College{

  List<Department> departmentList;

  public CollegeInfo() {
    departmentList=new ArrayList<>();
    addDepartment("java", "java 信息专业");
    addDepartment("python", "python 信息专业");
    addDepartment("js", "js 信息专业");
    addDepartment("运维", "运维 信息专业");

  }


  @Override
  public String getName() {
    return "信息学院";
  }

  @Override
  public void addDepartment(String name, String desc) {
    departmentList.add(new Department(name, desc));
  }

  @Override
  public Iterator createIterator() {
    return new IteratorInfo(departmentList);
  }
}
