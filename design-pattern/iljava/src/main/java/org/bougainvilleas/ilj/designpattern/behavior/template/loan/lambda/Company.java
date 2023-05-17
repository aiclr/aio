package org.bougainvilleas.ilj.designpattern.behavior.template.loan.lambda;

import org.bougainvilleas.ilj.designpattern.behavior.template.loan.ApplicationDenied;

import java.util.logging.Logger;

/**
 * 具体方法实现统一放到 Company
 * 可以为不同国家 不同城市 创建不同 Company
 */
public class Company {

  protected static final Logger log = Logger.getLogger(Company.class.getSimpleName());

  public void checkIdentityCompany() throws ApplicationDenied {
    log.info("在Companies House等注册公司数据库中查找相关信息");
  }

  public void checkProfitAndLoss() throws ApplicationDenied {
    log.info("评估公司的现有利润、损益表和资产负债表");
  }

  public void checkHistoricalDebtCompany() throws ApplicationDenied {
    log.info("查看现有的坏账和未偿债务");
  }
}
