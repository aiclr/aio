package org.bougainvilleas.ilj.designpattern.behavior.iterator;

import java.util.Iterator;

/**
 * 一种实现,管理迭代
 * Computer 遍历数组形式
 */
public class IteratorComputer implements Iterator<Department>{
  Department[] departments;
  int position = 0;

  public IteratorComputer(Department[] departments) {
    this.departments = departments;
  }

  @Override
  public boolean hasNext() {
    if (position >= departments.length || departments[position] == null) {
      return false;
    } else {
      return true;
    }
  }

  @Override
  public Department next() {
    Department department = departments[position];
    position += 1;
    return department;
  }

  @Override
  public void remove() {

  }
}
