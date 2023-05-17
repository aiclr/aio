package org.bougainvilleas.ilj.designpattern.behavior.memento;

/**
 * 需要保存状态的角色
 */
public class Originator {
  //状态信息
  private String state;

  /**
   * 保存一个状态对象Memento
   *
   * @return 状态对象Memento
   */
  public Memento saveStateMemento() {
    return new Memento(state);
  }

  /**
   * 恢复状态
   */
  public void getStateMemento(Memento memento) {
    setState(memento.getState());
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
