package org.bougainvilleas.ilj.principle.lod;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ManagerCollege {
  private static final Logger log = Logger.getLogger(ManagerCollege.class.getSimpleName());

  public List<EmployeeCollege> getAllEmployee() {
    List<EmployeeCollege> list = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      EmployeeCollege employee = new EmployeeCollege();
      employee.setId(i);
      list.add(employee);
    }
    return list;
  }

  public void printAllEmployee() {
    List<EmployeeCollege> employeeCollegeList = this.getAllEmployee();
    for (EmployeeCollege ce : employeeCollegeList) {
      log.info(Integer.toString(ce.getId()));
    }
  }
}
