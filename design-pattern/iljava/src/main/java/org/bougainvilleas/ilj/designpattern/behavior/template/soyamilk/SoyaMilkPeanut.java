package org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk;

/**
 * 花生豆浆
 */
public class SoyaMilkPeanut extends SoyaMilk {
  @Override
  public void addCondiments() {
    log.info("加入花生");
  }
}
