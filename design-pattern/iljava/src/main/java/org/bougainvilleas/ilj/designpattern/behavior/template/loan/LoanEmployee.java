package org.bougainvilleas.ilj.designpattern.behavior.template.loan;

//本行员工申请贷款
public class LoanEmployee extends LoanPerson {
  @Override
  protected void checkIncomeHistory() throws ApplicationDenied {
    log.info("银行员工不需要检查员工历史");
  }
}
