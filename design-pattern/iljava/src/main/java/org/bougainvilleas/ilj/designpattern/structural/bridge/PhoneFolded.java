package org.bougainvilleas.ilj.designpattern.structural.bridge;

/**
 * 折叠手机实现类
 * 方便扩展手机种类的时
 */
public class PhoneFolded extends Phone {
  public PhoneFolded(Brand brand) {
    super(brand);
  }

  @Override
  public void open() {
    super.open();
    log.info("FoldedPhone");
  }

  @Override
  public void close() {
    super.close();
    log.info("FoldedPhone");
  }

  @Override
  public void call() {
    super.call();
    log.info("FoldedPhone");
  }
}
