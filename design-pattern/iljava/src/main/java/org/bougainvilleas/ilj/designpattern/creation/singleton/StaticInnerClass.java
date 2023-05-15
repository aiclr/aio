package org.bougainvilleas.ilj.designpattern.creation.singleton;

public class StaticInnerClass {

  private StaticInnerClass() {
  }

  /**
   * 静态内部类
   * 涉及类的 主动使用 和 被动使用，以及类加载的 Initialization 阶段
   * {@link https://bougainvilleas.github.io/lotus/jvm/classloadersubsystem.html#类的主动使用和被动使用}
   *
   * 外部类调用静态内部类属于被动使用，静态内部类加载时不会执行类加载的 Initialization 阶段。
   * 故外部类调用静态内部类时，静态内部类开始执行类加载的 Initialization 阶段，即<clinit>()，此方法由jvm保证线程安全。
   */
  public static class Singleton{
    private static StaticInnerClass instance=new StaticInnerClass();
  }

  public static StaticInnerClass getInstance(){
    return Singleton.instance;
  }

}
