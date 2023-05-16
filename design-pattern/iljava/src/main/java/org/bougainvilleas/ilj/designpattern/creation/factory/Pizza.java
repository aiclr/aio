package org.bougainvilleas.ilj.designpattern.creation.factory;

import java.util.logging.Logger;

/**
 * 产品
 */
public class Pizza {

  protected static final Logger log= Logger.getLogger(Pizza.class.getSimpleName());

  String name;

  public void prepare(){}

  public void bake(){
    log.info(name+" bake");
  }

  public void cut(){
    log.info(name+" cut");
  }

  public void box(){
    log.info(name+" box");
  }


  public void setName(String name) {
    this.name = name;
  }
}
