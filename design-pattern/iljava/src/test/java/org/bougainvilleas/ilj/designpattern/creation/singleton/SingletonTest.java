package org.bougainvilleas.ilj.designpattern.creation.singleton;

import org.bougainvilleas.ilj.designpattern.creation.singleton.hungry.StaticBlock;
import org.bougainvilleas.ilj.designpattern.creation.singleton.hungry.StaticVariable;
import org.bougainvilleas.ilj.designpattern.creation.singleton.lazy.SyncMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.function.Supplier;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SingletonTest {

  Supplier<String> msgSup=()->"单例模式";

  private static final Logger log=Logger.getLogger(SingletonTest.class.getSimpleName());

  @DisplayName("同步代码块+双重检查实现懒汉式单例模式-效率高")
  @Execution(ExecutionMode.CONCURRENT)//多线程
  @RepeatedTest(5)
  void testSyncBlockDoubleCheck() {
    log.info(Thread.currentThread().getName()+"🚦"+ SyncMethod.getInstanceDoubleCheck().hashCode());
    assertSame(SyncMethod.getInstanceDoubleCheck(), SyncMethod.getInstanceDoubleCheck(), msgSup);
  }

  @DisplayName("懒汉式单例模式错误同步代码块示范")
  @Execution(ExecutionMode.CONCURRENT)//多线程
  @RepeatedTest(5)
  void testSyncBlockNotSafe() {
    log.info(Thread.currentThread().getName()+"🚦"+ SyncMethod.syncBlockNotSafe().hashCode());
    assertSame(SyncMethod.syncBlockNotSafe(), SyncMethod.syncBlockNotSafe(), msgSup);
  }

  @DisplayName("同步方法实现懒汉式单例模式-效率低")
  @Execution(ExecutionMode.CONCURRENT)//多线程
  @RepeatedTest(5)
  void testSyncMethod() {
    log.info(Thread.currentThread().getName()+"🚦"+ SyncMethod.getInstance().hashCode());
    assertSame(SyncMethod.getInstance(), SyncMethod.getInstance(), msgSup);
  }

  @DisplayName("利用枚举实现单例模式")
  @Test
  void testEnum() {
    log.info("🚦"+ SingletonEnum.INSTANCE.hashCode()+"🚦"+ SingletonEnum.INSTANCE2.hashCode()+"🚦"+ SingletonEnum.INSTANCE3.hashCode());
    assertSame(SingletonEnum.INSTANCE, SingletonEnum.INSTANCE, msgSup);
    assertSame(SingletonEnum.INSTANCE2, SingletonEnum.INSTANCE2, msgSup);
    assertSame(SingletonEnum.INSTANCE3, SingletonEnum.INSTANCE3, msgSup);

    assertSame(SingletonEnum.INSTANCE.instance, SingletonEnum.INSTANCE.instance, msgSup);
    assertSame(SingletonEnum.INSTANCE2.instance, SingletonEnum.INSTANCE2.instance, msgSup);
    assertSame(SingletonEnum.INSTANCE3.instance, SingletonEnum.INSTANCE3.instance, msgSup);

    assertNotSame(SingletonEnum.INSTANCE,SingletonEnum.INSTANCE2, msgSup);
    assertNotSame(SingletonEnum.INSTANCE.instance,SingletonEnum.INSTANCE2.instance, msgSup);
  }

  @DisplayName("利用静态内部类实现单例模式")
  @Test
  void testStaticInnerClass() {
    assertSame(StaticInnerClass.getInstance(), StaticInnerClass.getInstance(), msgSup);
  }

  @DisplayName("利用静态代码块实现恶汉式单例模式")
  @Test
  void testStaticBlock() {
    assertSame(StaticBlock.getInstance(), StaticBlock.getInstance(), msgSup);
  }

  @DisplayName("利用类变量/静态变量实现恶汉式单例模式")
  @Test
  void testStaticVariable() {
    assertSame(StaticVariable.getInstance(), StaticVariable.getInstance(), msgSup);
  }

}
