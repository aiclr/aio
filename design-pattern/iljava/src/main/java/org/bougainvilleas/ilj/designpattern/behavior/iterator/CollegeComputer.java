package org.bougainvilleas.ilj.designpattern.behavior.iterator;

import java.util.Iterator;

/**
 * 具体聚合持有对象集合,提供方法,返回一个可以正确遍历集合的迭代器,
 */
public class CollegeComputer implements College{

  Department[] departments;
  int numOfDepartment = 0;

  public CollegeComputer() {
    departments=new Department[4];
    addDepartment("java", "java 专业");
    addDepartment("python", "python 专业");
    addDepartment("js", "js 专业");
    addDepartment("运维", "运维 专业");
  }

  @Override
  public String getName() {
    return "计算机学院";
  }

  @Override
  public void addDepartment(String name, String desc) {
    departments[numOfDepartment] = new Department(name, desc);
    numOfDepartment += 1;
  }

  @Override
  public Iterator createIterator() {
    return new IteratorComputer(departments);
  }
}
