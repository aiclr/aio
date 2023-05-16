package org.bougainvilleas.ilj.designpattern.structural.bridge;

/**
 * 平板手机实现类
 * 方便扩展手机种类的时
 */
public class PhoneUpRight extends Phone {
  public PhoneUpRight(Brand brand) {
    super(brand);
  }

  @Override
  public void open() {
    super.open();
    log.info("UpRightPhone");
  }

  @Override
  public void close() {
    super.close();
    log.info("UpRightPhone");
  }

  @Override
  public void call() {
    super.call();
    log.info("UpRightPhone");
  }
}
