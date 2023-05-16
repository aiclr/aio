package org.bougainvilleas.ilj.designpattern.structural.facade;

import java.util.logging.Logger;

/**
 * 模拟功能模块
 */
public class Model1 {
  private static final Logger log = Logger.getLogger(Model1.class.getSimpleName());

  private Model1() {
  }

  private static Model1 INSTANCE = new Model1();

  public static Model1 getInstance() {
    return INSTANCE;
  }

  public void play(){
    log.info("Model1 is PLAY");
  }

  public void on(){
    log.info("Model1 is ON");
    play();
  }

  public void off(){
    log.info("Model1 is OFF");
  }

}
