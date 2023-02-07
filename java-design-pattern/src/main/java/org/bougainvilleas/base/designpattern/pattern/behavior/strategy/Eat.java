package org.bougainvilleas.base.designpattern.pattern.behavior.strategy;

/**
 * 如果策略带返回值 可以使用 lambda 简化 策略
 */
public interface Eat {
  String eat();
}

class GrassEat implements Eat{
  @Override
  public String eat() {
    return "草";
  }
}

class FishEat implements Eat{
  @Override
  public String eat() {
    return "鱼";
  }
}
