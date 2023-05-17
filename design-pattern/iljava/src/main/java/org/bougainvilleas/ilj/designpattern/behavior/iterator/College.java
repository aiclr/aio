package org.bougainvilleas.ilj.designpattern.behavior.iterator;

import java.util.Iterator;

/**
 * 统一聚合接口,将客户端与具体聚合解耦, 数据层
 */
public interface College {
  String getName();
  void addDepartment(String name,String desc);
  Iterator<Department> createIterator();
}
