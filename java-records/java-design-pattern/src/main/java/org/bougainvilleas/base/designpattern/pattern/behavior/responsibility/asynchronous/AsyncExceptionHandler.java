package org.bougainvilleas.base.designpattern.pattern.behavior.responsibility.asynchronous;

import java.util.logging.Level;
import java.util.logging.Logger;

public class AsyncExceptionHandler<T> implements Thread.UncaughtExceptionHandler {

  private static final Logger log=Logger.getLogger(AsyncExceptionHandler.class.getName());

  @Override
  public void uncaughtException(Thread thread, Throwable throwable) {
    log.log(Level.SEVERE,thread.getName()+thread.getId()+": 出现异常===>"+throwable.getMessage());
    //重启线程
  }
}
