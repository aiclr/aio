package org.bougainvilleas.ilj.designpattern.behavior.responsibility.concurrency;

import org.bougainvilleas.ilj.designpattern.behavior.responsibility.PurchaseRequest;

import java.util.concurrent.CountDownLatch;

/**
 * 校长 >30000
 */
public class ApproverSchoolMaster extends ApproverPro {
  public ApproverSchoolMaster(CountDownLatch begin, CountDownLatch end, PurchaseRequest purchaseRequest) {
    super(begin, end, purchaseRequest);
  }

  @Override
  public Boolean call() throws Exception {
    Thread.currentThread().setUncaughtExceptionHandler(new AsyncExceptionHandler<ApproverSchoolMaster>());
    begin.await();
    if(purchaseRequest.getPrice()>30000){
      log.info("校长 处理了"+purchaseRequest.getId());
      end.countDown();
      return Boolean.TRUE;
    }else {
      log.info("校长 无法处理"+purchaseRequest.getId());
      end.countDown();
      return Boolean.FALSE;
    }
  }
}
