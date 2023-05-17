package org.bougainvilleas.ilj.designpattern.behavior;

import org.bougainvilleas.ilj.designpattern.behavior.responsibility.Approver;
import org.bougainvilleas.ilj.designpattern.behavior.responsibility.ApproverCollege;
import org.bougainvilleas.ilj.designpattern.behavior.responsibility.ApproverDepartment;
import org.bougainvilleas.ilj.designpattern.behavior.responsibility.ApproverSchoolMaster;
import org.bougainvilleas.ilj.designpattern.behavior.responsibility.ApproverViceSchoolMaster;
import org.bougainvilleas.ilj.designpattern.behavior.responsibility.PurchaseRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Logger;

@DisplayName("责任链模式")
class ResponsibilityTest {

  private static final Logger log = Logger.getLogger(ResponsibilityTest.class.getSimpleName());

  @DisplayName("普通责任链模式")
  @Test
  void responsibilityTest() throws InterruptedException {
    long start = System.currentTimeMillis();
    PurchaseRequest request = new PurchaseRequest(1, 20000.0f, 100);
    Approver department = new ApproverDepartment("教学主任");
    Approver college = new ApproverCollege("院长");
    Approver viceSchoolMaster = new ApproverViceSchoolMaster("副校长");
    Approver schoolMaster = new ApproverSchoolMaster("校长");

    department.setApprover(college);
    college.setApprover(viceSchoolMaster);
    viceSchoolMaster.setApprover(schoolMaster);
    schoolMaster.setApprover(department);

    schoolMaster.processRequest(request);
    log.info((System.currentTimeMillis() - start)+"ms");
  }

  @DisplayName("并发改造-非责任链模式")
  @Test
  void asyncTest() throws InterruptedException, ExecutionException {
    long start = System.currentTimeMillis();
    int number = 4;
    CountDownLatch begin = new CountDownLatch(1);
    CountDownLatch end = new CountDownLatch(number);
    ExecutorService executorService = Executors.newFixedThreadPool(number);

    PurchaseRequest purchaseRequest = new PurchaseRequest(1, 20000.0f, 100);

    List<Future<Boolean>> futureList = new ArrayList<>();

    futureList.add(executorService.submit(new org.bougainvilleas.ilj.designpattern.behavior.responsibility.concurrency.ApproverCollege(begin, end, purchaseRequest)));
    futureList.add(executorService.submit(new org.bougainvilleas.ilj.designpattern.behavior.responsibility.concurrency.ApproverDepartment(begin, end, purchaseRequest)));
    futureList.add(executorService.submit(new org.bougainvilleas.ilj.designpattern.behavior.responsibility.concurrency.ApproverSchoolMaster(begin, end, purchaseRequest)));
    futureList.add(executorService.submit(new org.bougainvilleas.ilj.designpattern.behavior.responsibility.concurrency.ApproverViceSchoolMaster(begin, end, purchaseRequest)));

    begin.countDown();
    end.await();

    for (Future<Boolean> future : futureList) {
      if (future.get()) {
        log.info("yes");
      } else {
        log.info("no");
      }
    }
    executorService.shutdown();
    log.info((System.currentTimeMillis() - start)+"ms");
  }
}
