package org.bougainvilleas.ilj.designpattern.structural.flyweight;

/**
 * 不共享内部状态
 */
public class StateUnshared {

  private String data;

  public StateUnshared(String data) {
    this.data = data;
  }

  public String getData() {
    return data;
  }
}
