package org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk;

/**
 * 花生豆浆
 */
public class SoyaMilkPure extends SoyaMilk {
  @Override
  public void addCondiments() {
    //do nothing 空方法
  }
  @Override
  public boolean hook() {
    return false;
  }
}
