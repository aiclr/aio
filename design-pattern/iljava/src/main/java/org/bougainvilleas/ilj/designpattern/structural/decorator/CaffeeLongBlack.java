package org.bougainvilleas.ilj.designpattern.structural.decorator;

/**
 * 具体咖啡类，扩展很方便
 */
public class CaffeeLongBlack extends Caffee {

  public CaffeeLongBlack() {
    setCost(10.0f);
    setDes("LongBlack Caffee");
  }
}
