package org.bougainvilleas.base.designpattern.pattern.behavior.responsibility.asynchronous;

import org.bougainvilleas.base.designpattern.pattern.behavior.responsibility.PurchaseRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Client {
  public static void main(String[] args) throws InterruptedException, ExecutionException {
    long start=System.currentTimeMillis();
    int number=4;
    CountDownLatch begin=new CountDownLatch(1);
    CountDownLatch end=new CountDownLatch(number);

    ExecutorService executorService = Executors.newFixedThreadPool(number);

    PurchaseRequest purchaseRequest = new PurchaseRequest(1, 20000.0f, 100);

    List<Future<Boolean>> futureList=new ArrayList<>();

    futureList.add(executorService.submit(new College(begin, end, purchaseRequest)));
    futureList.add(executorService.submit(new Department(begin, end, purchaseRequest)));
    futureList.add(executorService.submit(new SchoolMaster(begin, end, purchaseRequest)));
    futureList.add(executorService.submit(new ViceSchoolMaster(begin, end, purchaseRequest)));

    begin.countDown();
    end.await();

    for(Future<Boolean> future:futureList){
      if(future.get()){
        System.err.println("yes");
      }else {
        System.err.println("no");
      }
    }
    executorService.shutdown();
    System.err.println(System.currentTimeMillis()-start);
  }
}
