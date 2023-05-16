package org.bougainvilleas.ilj.designpattern.creation.builder;

/**
 * 抽象建造者
 * 建造过程的抽象
 * <p>
 * 建造者模式相关jdk源码
 * {@link StringBuilder} extends {@link AbstractStringBuilder}。
 * {@link AbstractStringBuilder} implements {@link Appendable}。
 * <p>
 * {@link Appendable} 接口定义了多个append方法：抽象建造者，定义抽象方法。
 * {@link Appendable#append(char)}；
 * {@link Appendable#append(CharSequence)}；
 * {@link Appendable#append(CharSequence, int, int)}；
 * <p>
 * {@link AbstractStringBuilder} implements {@link Appendable}：具体建造者，但不能实例化
 * <p>
 * {@link StringBuilder} extends {@link AbstractStringBuilder}：即是指挥者，又是建造者。
 * 建造方法{@link StringBuilder#append(CharSequence)}具体由{@link AbstractStringBuilder}实现
 */
public abstract class BuilderHouse {
  House house = new House();

  public abstract void buildBasic();

  public abstract void buildWalls();

  public abstract void roofed();

  public House build() {
    return house;
  }
}
