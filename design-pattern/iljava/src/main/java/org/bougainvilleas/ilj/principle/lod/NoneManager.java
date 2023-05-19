package org.bougainvilleas.ilj.principle.lod;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 直接朋友{@link Employee} {@link NoneManagerCollege}
 * 陌生类 {@link EmployeeCollege}
 */
public class NoneManager {
  private static final Logger log = Logger.getLogger(NoneManager.class.getSimpleName());

  public List<Employee> getAllEmployee() {
    List<Employee> list = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      Employee employee = new Employee();
      employee.setId(i);
      list.add(employee);
    }
    return list;
  }

  /**
   * NoneEmployeeCollege 非朋友关系 可以改进
   */
  public void printAllEmployee(NoneManagerCollege cm) {

    //NoneEmployeeCollege  违背迪米特法则
    List<EmployeeCollege> employeeCollegeList = cm.getAllEmployee();
    for (EmployeeCollege ce : employeeCollegeList) {
      log.info(Integer.toString(ce.getId()));
    }
    log.info("------");
    List<Employee> employeeList = this.getAllEmployee();
    for (Employee e : employeeList) {
      log.info(Integer.toString(e.getId()));
    }
  }
}
