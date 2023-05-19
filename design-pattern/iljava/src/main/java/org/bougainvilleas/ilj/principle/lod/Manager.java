package org.bougainvilleas.ilj.principle.lod;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 直接朋友{@link Employee} {@link ManagerCollege}
 */
public class Manager {
  private static final Logger log = Logger.getLogger(Manager.class.getSimpleName());

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
   * {@link ManagerCollege}自己处理输出逻辑，只暴露结果方法{@link ManagerCollege#printAllEmployee()}出来
   */
  public void printAllEmployee(ManagerCollege cm) {
    cm.printAllEmployee();
    log.info("------");
    List<Employee> employeeList = this.getAllEmployee();
    for (Employee e : employeeList) {
      log.info(Integer.toString(e.getId()));
    }
  }
}
