package org.bougainvilleas.ilj.designpattern.behavior.visitor;

/**
 * 具体元素{@link ElementMan}和{@link ElementWoman}实现accept方法
 */
public class VisitorFail extends Visitor{
  @Override
  public void manVisitor(ElementMan man) {
    log.info("Man FAIL");
  }

  @Override
  public void womanVisitor(ElementWoman woman) {
    log.info("Woman FAIL");
  }
}
