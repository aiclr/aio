package org.bougainvilleas.ilj.designpattern.structural.facade;

import java.util.logging.Logger;

/**
 * 模拟功能模块
 */
public class Model2 {
  private static final Logger log = Logger.getLogger(Model2.class.getSimpleName());

  private Model2() {
  }

  private static Model2 INSTANCE = new Model2();

  public static Model2 getInstance() {
    return INSTANCE;
  }

  public void play() {
    log.info("Model2 is PLAY");
    Model3.getInstance().on();
    Model3.getInstance().play();
  }

  public void start() {
    log.info("Model2 is START");
    play();
  }

  public void stop() {
    Model3.getInstance().off();
    log.info("Model2 is STOP");
  }


}
