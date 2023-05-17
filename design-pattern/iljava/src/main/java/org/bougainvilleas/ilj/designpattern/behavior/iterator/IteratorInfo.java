package org.bougainvilleas.ilj.designpattern.behavior.iterator;

import java.util.Iterator;
import java.util.List;

/**
 * 一种实现,管理迭代
 * Info 遍历集合形式
 */
public class IteratorInfo implements Iterator<Department> {
  List<Department> departmentList;

  int index = -1;

  public IteratorInfo(List<Department> departmentList) {
    this.departmentList = departmentList;
  }

  @Override
  public boolean hasNext() {
    if (index >= departmentList.size() - 1) {
      return false;
    } else {
      index += 1;
      return true;
    }
  }

  @Override
  public Department next() {
    return departmentList.get(index);
  }

  @Override
  public void remove() {
    //空实现
  }
}
