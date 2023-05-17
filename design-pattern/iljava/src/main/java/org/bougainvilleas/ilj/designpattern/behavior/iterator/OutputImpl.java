package org.bougainvilleas.ilj.designpattern.behavior.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

public class OutputImpl {
  private static final Logger log = Logger.getLogger(OutputImpl.class.getSimpleName());
  List<College> collegeList;

  public OutputImpl(List<College> collegeList) {
    this.collegeList = collegeList;
  }

  public void printCollege() {
    for (College college : collegeList) {
      log.info(college.getName());
      printDepartment(college.createIterator());
    }

  }


  public void printDepartment(Iterator<Department> iterator) {
    while (iterator.hasNext()) {
      Department next = iterator.next();
      log.info(next.getDesc());
    }
  }
}
