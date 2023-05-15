package org.bougainvilleas.ilj.designpattern.creation.singleton.hungry;

/**
 * 静态代码块实现 单例模式 饿汉式
 *
 * 实现原理
 * 类加载过程 Loading、Linking、Initialization
 *  Linking阶段的 Prepare阶段会为静态变量赋默认初始值。
 *  Initialization阶段，当存在静态变量赋值，静态代码块赋值时
 *    javac编译器自动收集类中的所有类静态变量的赋值动作和静态代码块中的语句合并成类构造器方法<clinit>()
 *
 *  jvm被要求必须保证一个类的<clinit>()方法在多线程下被同步加锁。
 *  类只会被加载一次，并放置到method area
 *
 * {@link https://bougainvilleas.github.io/lotus/jvm/classloadersubsystem.html#prepare}
 */
public class StaticBlock {
  private StaticBlock() {

  }

  private static StaticBlock INSTANCE;

  static {
    INSTANCE = new StaticBlock();
  }

  public static StaticBlock getInstance() {
    return INSTANCE;
  }


}
