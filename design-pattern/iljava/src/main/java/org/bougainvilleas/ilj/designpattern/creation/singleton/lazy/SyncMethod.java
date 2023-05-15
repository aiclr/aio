package org.bougainvilleas.ilj.designpattern.creation.singleton.lazy;

/**
 * 懒汉式单例模式
 */
public class SyncMethod {
  private SyncMethod() {
  }

  private static SyncMethod INSTANCE;
  /**
   * volatile 防止jvm字节码指令重排，导致多线程不安全
   */
  private static volatile SyncMethod INSTANCE_DoubleCheck;

  /**
   * 同步方法 线程安全 效率低
   */
  public static synchronized SyncMethod getInstance() {
    if (INSTANCE == null)
      INSTANCE = new SyncMethod();
    return INSTANCE;
  }

  /**
   * 同步代码块 fixme 线程不安全
   */
  public static SyncMethod syncBlockNotSafe() {
    if (INSTANCE == null) {
      synchronized (SyncMethod.class) {
        INSTANCE = new SyncMethod();
      }
    }
    return INSTANCE;
  }

  /**
   * 同步代码块+双重检查 线程安全 效率高
   */
  public static SyncMethod getInstanceDoubleCheck() {
    if (INSTANCE_DoubleCheck == null) {
      synchronized (SyncMethod.class) {
        if (INSTANCE_DoubleCheck == null)
          INSTANCE_DoubleCheck = new SyncMethod();
      }
    }
    return INSTANCE_DoubleCheck;
  }
}
