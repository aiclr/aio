package org.bougainvilleas.ilj.designpattern.structural.bridge;

import java.util.logging.Logger;

/**
 * 高层实现类
 * 桥接模式
 */
public class Phone {
  protected static final Logger log = Logger.getLogger(Phone.class.getSimpleName());
  private Brand brand;

  public Phone(Brand brand) {
    this.brand = brand;
  }

  public void open() {
    this.brand.open();
  }

  public void close() {
    this.brand.close();
  }

  public void call() {
    this.brand.call();
  }
}
