package org.bougainvilleas.ilj.designpattern.structural.bridge;

public class BrandIPhone implements Brand {
  @Override
  public void open() {
    log.info("iphone is call");
  }

  @Override
  public void close() {
    log.info("iphone is call");
  }

  @Override
  public void call() {
    log.info("iphone is call");
  }
}
