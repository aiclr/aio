package org.bougainvilleas.ilj.designpattern.behavior.memento;

/**
 * 记录状态
 */
public class Memento {
  private String state;

  public Memento(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }

  public void getStateMemento(Memento memento) {
    state = memento.state;
  }

}
