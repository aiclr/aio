package org.bougainvilleas.ilj.designpattern.structural.facade;

import java.util.logging.Logger;

/**
 * 模拟功能模块
 */
public class Model3 {
  private static final Logger log = Logger.getLogger(Model3.class.getSimpleName());

  private Model3() {
  }

  private static Model3 INSTANCE = new Model3();

  public static Model3 getInstance() {
    return INSTANCE;
  }

  public void play(){
    log.info("Model3 is PLAY");
  }

  public void on(){
    log.info("Model3 is ON");
  }

  public void off(){
    log.info("Model3 is OFF");
  }

}
