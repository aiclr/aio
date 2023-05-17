package org.bougainvilleas.ilj.designpattern.behavior.responsibility.concurrency;

import org.bougainvilleas.ilj.designpattern.behavior.responsibility.PurchaseRequest;

import java.util.concurrent.CountDownLatch;

/**
 * 教学主任 审批<=5000
 */
public class ApproverDepartment extends ApproverPro {
  public ApproverDepartment(CountDownLatch begin, CountDownLatch end, PurchaseRequest purchaseRequest) {
    super(begin, end, purchaseRequest);
  }

  @Override
  public Boolean call() throws Exception {
    Thread.currentThread().setUncaughtExceptionHandler(new AsyncExceptionHandler<ApproverDepartment>());
    begin.await();
    if(purchaseRequest.getPrice()<=5000){
      log.info("教学主任 处理了"+purchaseRequest.getId());
      end.countDown();
      return Boolean.TRUE;
    }else {
      log.info("教学主任 无法处理"+purchaseRequest.getId());
      end.countDown();
      return Boolean.FALSE;
    }
  }
}
