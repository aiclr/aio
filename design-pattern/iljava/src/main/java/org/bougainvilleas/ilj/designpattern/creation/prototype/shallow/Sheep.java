package org.bougainvilleas.ilj.designpattern.creation.prototype.shallow;

/**
 * 原型模式
 * 浅拷贝
 */
public class Sheep implements Cloneable{
  private String name;
  private String color;
  private int age;
  private Sheep sheep;

  public Sheep(String name, String color, int age) {
    this.name = name;
    this.color = color;
    this.age = age;
  }

  public Sheep getSheep() {
    return sheep;
  }

  public void setSheep(Sheep sheep) {
    this.sheep = sheep;
  }

  /**
   * 浅拷贝，内部引用类型属性不会拷贝
   * @return
   * @throws CloneNotSupportedException
   */
  @Override
  protected Object clone() throws CloneNotSupportedException {
    return super.clone();
  }

  @Override
  public String toString() {
    return hashCode()+"--Sheep{" +
            "name='" + name + '\'' +
            ", color='" + color + '\'' +
            ", age=" + age +
            ", sheep hashcode=" + sheep.hashCode() +
            '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }
}
