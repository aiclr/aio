package org.bougainvilleas.ilj.principle.dip;

public class User {

  private Receiver receiver;

  /**
   * 1 通过 构造方法 实现依赖关系传递
   */
  public User(Receiver receiver) {
    this.receiver = receiver;
  }

  /**
   * 2 通过 setter方法 实现依赖关系传递
   */
  public void setReceiver(Receiver receiver) {
    this.receiver = receiver;
  }

  public String receive(){
    return receiver.getInfo();
  }


  public User() {
  }
  /**
   * 3 通过 接口传递 实现依赖关系传递
   */
  public String receive(Receiver receiver) {
    return receiver.getInfo();
  }
}
