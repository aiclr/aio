package org.bougainvilleas.ilj.designpattern.behavior.template.loan.lambda;

import org.bougainvilleas.ilj.designpattern.behavior.template.loan.ApplicationDenied;

import java.util.logging.Logger;

/**
 * 具体方法实现统一放到 Person
 * 可以为不同国家 不同城市 创建不同 Person
 */
public class Person {

  protected static final Logger log = Logger.getLogger(Person.class.getSimpleName());

  public void checkIdentityPerson() throws ApplicationDenied {
    log.info("分析客户提供的纸本结算单，确认客户地址是否真实有效");
  }

  public void checkIncomeHistoryPerson() throws ApplicationDenied {
    log.info("检查工资条判断客户是否仍被雇佣");
  }

  public void checkIncomeHistoryEmployee() throws ApplicationDenied {
    System.err.println("银行员工不需要检查员工历史");
  }

  public void checkHistoricalDebtPerson() throws ApplicationDenied {
    log.info("将工作交给外部的信用卡支付提供商");
  }
}
