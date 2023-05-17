package org.bougainvilleas.ilj.designpattern.behavior.template.loan.lambda;

import org.bougainvilleas.ilj.designpattern.behavior.template.loan.ApplicationDenied;

import java.util.logging.Logger;

/**
 * 申请贷款流程 传统模板方法 实现方案
 */
public class LoanLambda {
  protected static final Logger log = Logger.getLogger(LoanLambda.class.getSimpleName());

  private final Criteria identity;
  private final Criteria incomeHistory;
  private final Criteria creditHistory;

  public LoanLambda(Criteria identity, Criteria incomeHistory, Criteria creditHistory) {
    this.identity = identity;
    this.incomeHistory = incomeHistory;
    this.creditHistory = creditHistory;
  }

  public void check() throws ApplicationDenied {
    identity.check();
    incomeHistory.check();
    creditHistory.check();
    reportFindings();
  }


  protected void reportFindings() {
    log.info("通过");
  }
}

