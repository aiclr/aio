package org.bougainvilleas.base.designpattern.pattern.behavior.template.loan;

//申请贷款流程 传统模板方法 实现方案
public abstract class LoanApplication {

  public void checkLoanApplication() throws ApplicationDenied {
    checkIdentity();
    checkIncomeHistory();
    checkCreditHistory();
    reportFindings();
  }

  protected abstract void checkIdentity() throws ApplicationDenied;

  protected abstract void checkIncomeHistory() throws ApplicationDenied;

  protected abstract void checkCreditHistory() throws ApplicationDenied;

  protected void reportFindings() {
    System.err.println("通过");
  }
}

// 公司申请贷款
class CompanyLoanApplication extends LoanApplication{
  @Override
  protected void checkIdentity() throws ApplicationDenied {
    System.err.println("在Companies House等注册公司数据库中查找相关信息");
  }

  @Override
  protected void checkIncomeHistory() throws ApplicationDenied {
    System.err.println("评估公司的现有利润、损益表和资产负债表");
  }

  @Override
  protected void checkCreditHistory() throws ApplicationDenied {
    System.err.println("查看现有的坏账和未偿债务");
  }
}

// 个人申请贷款
class PersonalLoanApplication extends LoanApplication{
  @Override
  protected void checkIdentity() throws ApplicationDenied {
    System.err.println("分析客户提供的纸本结算单，确认客户地址是否真实有效");
  }

  @Override
  protected void checkIncomeHistory() throws ApplicationDenied {
    System.err.println("检查工资条判断客户是否仍被雇佣");
  }

  @Override
  protected void checkCreditHistory() throws ApplicationDenied {
    System.err.println("将工作交给外部的信用卡支付提供商");
  }
}

//本行员工申请贷款
class EmployeeLoanApplication extends PersonalLoanApplication{
  @Override
  protected void checkIncomeHistory() throws ApplicationDenied {
    System.err.println("银行员工不需要检查员工历史");
  }
}

class ApplicationDenied extends RuntimeException {
  private static final long serialVersionUID = 1L;
}

class Client{
  public static void main(String[] args) {
    LoanApplication companyLoanApplication = new CompanyLoanApplication();
    companyLoanApplication.checkLoanApplication();
    LoanApplication personalLoanApplication = new PersonalLoanApplication();
    personalLoanApplication.checkLoanApplication();
    LoanApplication employeeLoanApplication = new EmployeeLoanApplication();
    employeeLoanApplication.checkLoanApplication();
  }
}