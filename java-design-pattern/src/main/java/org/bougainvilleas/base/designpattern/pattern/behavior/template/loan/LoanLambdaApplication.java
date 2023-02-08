package org.bougainvilleas.base.designpattern.pattern.behavior.template.loan;

/**
 * lambda 简化 模板方法模式
 * 模板方法是将一组方法调用按一定顺序组织起来
 * 使用函数接口表示函数
 * 使用lambda表达式或者方法引用实现这些接口，比使用继承构建算法更加灵活
 */
public class LoanLambdaApplication {
  private final Criteria identity;
  private final Criteria incomeHistory;
  private final Criteria creditHistory;

  public LoanLambdaApplication(Criteria identity, Criteria incomeHistory, Criteria creditHistory) {
    this.identity = identity;
    this.incomeHistory = incomeHistory;
    this.creditHistory = creditHistory;
  }

  public void checkLoanApplication() throws ApplicationDenied {
    identity.check();
    incomeHistory.check();
    creditHistory.check();
    reportFindings();
  }

  private void reportFindings() {
    System.err.println("通过");
  }
}


//如果申请失败，函数接口Criteria 抛出异常
interface Criteria {
  void check() throws ApplicationDenied;
}

/**
 * 具体方法实现统一放到 Company
 * 可以为不同国家 不同城市 创建不同 Company
 */
class Company {

  public void checkIdentityCompany() throws ApplicationDenied {
    System.err.println("在Companies House等注册公司数据库中查找相关信息");
  }

  public void checkProfitAndLoss() throws ApplicationDenied {
    System.err.println("评估公司的现有利润、损益表和资产负债表");
  }

  public void checkHistoricalDebtCompany() throws ApplicationDenied {
    System.err.println("查看现有的坏账和未偿债务");
  }
}
class Person {

  public void checkIdentityPerson() throws ApplicationDenied {
    System.err.println("分析客户提供的纸本结算单，确认客户地址是否真实有效");
  }

  public void checkIncomeHistoryPerson() throws ApplicationDenied {
    System.err.println("检查工资条判断客户是否仍被雇佣");
  }

  public void checkIncomeHistoryEmployee() throws ApplicationDenied {
    System.err.println("银行员工不需要检查员工历史");
  }

  public void checkHistoricalDebtPerson() throws ApplicationDenied {
    System.err.println("将工作交给外部的信用卡支付提供商");
//    throw new ApplicationDenied();
  }
}

class ClientLambda {
  public static void main(String[] args) {

    Company company = new Company();
    Person person = new Person();
    // 公司申请贷款
    LoanLambdaApplication companyLoanApplication =
            new LoanLambdaApplication(() -> company.checkIdentityCompany(), company::checkProfitAndLoss, company::checkHistoricalDebtCompany);
    companyLoanApplication.checkLoanApplication();
    // 个人申请贷款
    LoanLambdaApplication personLoanApplication =
            new LoanLambdaApplication(person::checkIdentityPerson, person::checkIncomeHistoryPerson, person::checkHistoricalDebtPerson);
    personLoanApplication.checkLoanApplication();
    //银行雇员申请贷款
    LoanLambdaApplication employeeLoanApplication =
            new LoanLambdaApplication(person::checkIdentityPerson, person::checkIncomeHistoryEmployee, person::checkHistoricalDebtPerson);
    employeeLoanApplication.checkLoanApplication();
  }
}