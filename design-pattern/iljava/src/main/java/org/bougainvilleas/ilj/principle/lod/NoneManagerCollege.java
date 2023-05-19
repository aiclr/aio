package org.bougainvilleas.ilj.principle.lod;

import java.util.ArrayList;
import java.util.List;

public class NoneManagerCollege {

  public List<EmployeeCollege> getAllEmployee() {
    List<EmployeeCollege> list = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      EmployeeCollege employee = new EmployeeCollege();
      employee.setId(i);
      list.add(employee);
    }
    return list;
  }
}
