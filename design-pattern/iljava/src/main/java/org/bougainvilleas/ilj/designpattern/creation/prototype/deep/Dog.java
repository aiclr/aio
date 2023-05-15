package org.bougainvilleas.ilj.designpattern.creation.prototype.deep;

import java.io.Serializable;

public class Dog implements Serializable, Cloneable{
  private static final long serialVersionUID=1L;
  private String name;

  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  public Dog(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
