package org.bougainvilleas.ilj.designpattern.structural.decorator;

/**
 * 具体咖啡类，扩展很方便
 */
public class CaffeeDecaf extends Caffee {

  public CaffeeDecaf() {
    setCost(4.0f);
    setDes("Decaf Caffee");
  }
}
