package org.bougainvilleas.ilj.designpattern.structural.bridge;

public class BrandXiaomi implements Brand {
  @Override
  public void open() {
    log.info("xiaomi is open");
  }

  @Override
  public void close() {
    log.info("xiaomi is closed");
  }

  @Override
  public void call() {
    log.info("xiaomi is call");
  }
}
