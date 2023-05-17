package org.bougainvilleas.ilj.designpattern.behavior.template.loan;

/**
 * 个人申请贷款
 */
public class LoanPerson extends Loan {
  @Override
  protected void checkIdentity() throws ApplicationDenied {
    log.info("分析客户提供的纸本结算单，确认客户地址是否真实有效");
  }

  @Override
  protected void checkIncomeHistory() throws ApplicationDenied {
    log.info("检查工资条判断客户是否仍被雇佣");
  }

  @Override
  protected void checkCreditHistory() throws ApplicationDenied {
    log.info("将工作交给外部的信用卡支付提供商");
  }
}
