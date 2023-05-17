package org.bougainvilleas.ilj.designpattern.behavior;

import org.bougainvilleas.ilj.designpattern.behavior.template.loan.Loan;
import org.bougainvilleas.ilj.designpattern.behavior.template.loan.LoanCompany;
import org.bougainvilleas.ilj.designpattern.behavior.template.loan.LoanEmployee;
import org.bougainvilleas.ilj.designpattern.behavior.template.loan.LoanPerson;
import org.bougainvilleas.ilj.designpattern.behavior.template.loan.lambda.Company;
import org.bougainvilleas.ilj.designpattern.behavior.template.loan.lambda.LoanLambda;
import org.bougainvilleas.ilj.designpattern.behavior.template.loan.lambda.Person;
import org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk.SoyaMilk;
import org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk.SoyaMilkPeanut;
import org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk.SoyaMilkPure;
import org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk.SoyaMilkRedBean;
import org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk.lambda.Condiments;
import org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk.lambda.SoyaMilkLambda;
import org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk.lambda.SoyaMilkLambdaPro;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("模板方法模式")
class TemplateTest {

  @Test
  @DisplayName("普通模板方法模式-soyaMilk")
  void soyaMilkTest() {

    SoyaMilk redBeanSoyaMilk = new SoyaMilkRedBean();
    redBeanSoyaMilk.make();

    SoyaMilk peanutSoyamilk = new SoyaMilkPeanut();
    peanutSoyamilk.make();

    SoyaMilk pureSoyamilk = new SoyaMilkPure();
    pureSoyamilk.make();
  }

  @Test
  @DisplayName("lambda增强型模板方法模式-soyaMilk")
  void soyaMilkLambdaTest() {
    Condiments condiment = new Condiments();
    SoyaMilkLambda pureSoyaMilk = new SoyaMilkLambda(
            condiment::select, false, null, condiment::soak, condiment::beat
    );
    pureSoyaMilk.make();

    SoyaMilkLambda redBeanSoyaMilk = new SoyaMilkLambda(
            condiment::select, true, condiment::redBean, condiment::soak, condiment::beat
    );
    redBeanSoyaMilk.make();

    /**
     * 保证构造器参数顺序
     */
    SoyaMilkLambdaPro peanutSoyamilk = new SoyaMilkLambdaPro(
            condiment::select, condiment::redBean, condiment::peanut, condiment::soak, condiment::beat
    );
    peanutSoyamilk.make();
  }

  @Test
  @DisplayName("普通模板方法模式-loan")
  void loanTest() {
    Loan company = new LoanCompany();
    /**
     * 公司申请贷款
     */
    company.check();

    Loan person = new LoanPerson();
    /**
     * 个人申请贷款
     */person.check();

    Loan employee = new LoanEmployee();
    /**
     * 银行雇员申请贷款
     */
    employee.check();
  }

  @Test
  @DisplayName("lambda模板方法模式-loan")
  void loanLambdaTest() {
    Company company = new Company();
    Person person = new Person();
    /**
     * 公司申请贷款
     */
    LoanLambda companyLoan = new LoanLambda(
            () -> company.checkIdentityCompany(),
            company::checkProfitAndLoss,
            company::checkHistoricalDebtCompany);
    companyLoan.check();
    /**
     * 个人申请贷款
     */
    LoanLambda personLoan = new LoanLambda(
            person::checkIdentityPerson,
            person::checkIncomeHistoryPerson,
            person::checkHistoricalDebtPerson);
    personLoan.check();
    /**
     * 银行雇员申请贷款
     */
    LoanLambda employeeLoan = new LoanLambda(
            person::checkIdentityPerson,
            person::checkIncomeHistoryEmployee,
            person::checkHistoricalDebtPerson);
    employeeLoan.check();
  }
}
