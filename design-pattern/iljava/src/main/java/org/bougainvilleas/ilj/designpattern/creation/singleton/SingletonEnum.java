package org.bougainvilleas.ilj.designpattern.creation.singleton;

/**
 * 利用枚举实现单例模式
 * todo
 */
public enum SingletonEnum {
  INSTANCE(new Singleton()),
  INSTANCE2(new Singleton()),
  INSTANCE3(new Singleton2());

  Object instance;

  SingletonEnum(Object instance) {
    this.instance=instance;
  }

  public static class Singleton{
    private Singleton() {
    }
  }

  public static class Singleton2{
    private Singleton2() {
    }
  }

}
