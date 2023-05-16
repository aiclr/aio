package org.bougainvilleas.ilj.designpattern.structural.decorator;

/**
 * 饮料基类
 */
public abstract class Drink {

  private String des;
  private float cost;

  //子类实现递归价格
  public abstract float cost();

  public void setDes(String des) {
    this.des = des;
  }

  public float getCost() {
    return cost;
  }

  public void setCost(float cost) {
    this.cost = cost;
  }

  public String getDes() {
    return des;
  }
}
