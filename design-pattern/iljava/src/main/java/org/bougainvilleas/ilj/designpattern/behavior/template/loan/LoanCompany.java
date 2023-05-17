package org.bougainvilleas.ilj.designpattern.behavior.template.loan;

/**
 * 公司申请贷款
 */
public class LoanCompany extends Loan {
  @Override
  protected void checkIdentity() throws ApplicationDenied {
    log.info("在Companies House等注册公司数据库中查找相关信息");
  }

  @Override
  protected void checkIncomeHistory() throws ApplicationDenied {
    log.info("评估公司的现有利润、损益表和资产负债表");
  }

  @Override
  protected void checkCreditHistory() throws ApplicationDenied {
    log.info("查看现有的坏账和未偿债务");
  }
}
