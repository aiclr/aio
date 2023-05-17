package org.bougainvilleas.ilj.designpattern.behavior.responsibility.concurrency;

import org.bougainvilleas.ilj.designpattern.behavior.responsibility.PurchaseRequest;

import java.util.concurrent.CountDownLatch;

/**
 * 院长审批 <=10000
 */
public class ApproverCollege extends ApproverPro {
  public ApproverCollege(CountDownLatch begin, CountDownLatch end, PurchaseRequest purchaseRequest) {
    super(begin, end, purchaseRequest);
  }

  @Override
  public Boolean call() throws Exception {
    Thread.currentThread().setUncaughtExceptionHandler(new AsyncExceptionHandler<ApproverCollege>());
    begin.await();
    if(purchaseRequest.getPrice()>5000 && purchaseRequest.getPrice()<=10000){
      log.info("院长 处理了"+purchaseRequest.getId());
      end.countDown();
      return Boolean.TRUE;
    }else {
      log.info("院长 无法处理"+purchaseRequest.getId());
      end.countDown();
      return Boolean.FALSE;
    }
  }
}
