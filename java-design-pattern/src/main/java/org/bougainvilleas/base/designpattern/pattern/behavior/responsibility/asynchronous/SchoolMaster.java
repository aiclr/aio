package org.bougainvilleas.base.designpattern.pattern.behavior.responsibility.asynchronous;

import org.bougainvilleas.base.designpattern.pattern.behavior.responsibility.PurchaseRequest;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SchoolMaster implements Callable<Boolean> {
  private CountDownLatch begin;
  private CountDownLatch end;
  private PurchaseRequest purchaseRequest;

  public SchoolMaster(CountDownLatch begin, CountDownLatch end, PurchaseRequest purchaseRequest) {
    this.begin = begin;
    this.end = end;
    this.purchaseRequest = purchaseRequest;
  }


  @Override
  public Boolean call() throws Exception {
    Thread.currentThread().setUncaughtExceptionHandler(new AsyncExceptionHandler<SchoolMaster>());
    begin.await();
    TimeUnit.SECONDS.sleep(3);
    if(purchaseRequest.getPrice()<=30000){
      System.err.println("College 处理了"+purchaseRequest.getId());
      end.countDown();
      return Boolean.TRUE;
    }else {
      System.err.println("College 无法处理"+purchaseRequest.getId());
      end.countDown();
      return Boolean.FALSE;
    }
  }
}
