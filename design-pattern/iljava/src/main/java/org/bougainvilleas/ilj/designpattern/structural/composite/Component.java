package org.bougainvilleas.ilj.designpattern.structural.composite;

import java.util.logging.Logger;

/**
 * 组合模式 公共类
 */
public abstract class Component {

  protected static final Logger log= Logger.getLogger(Component.class.getSimpleName());

  protected String name;
  protected String desc;

  /**
   * 叶子节点不需要实现，所以不设置成抽象
   * @param component
   */
  public void add(Component component){
    //        默认实现
    throw new UnsupportedOperationException();
  }

  /**
   * 叶子节点不需要实现 所以不设置成抽象
   * @param component
   */
  public void remove(Component component){
    //        默认实现
    throw new UnsupportedOperationException();

  }

  /**
   * 所有节点都需要的方法，所以设置成抽象
   */
  public abstract void print();


  public Component(String name, String desc) {
    this.name = name;
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
