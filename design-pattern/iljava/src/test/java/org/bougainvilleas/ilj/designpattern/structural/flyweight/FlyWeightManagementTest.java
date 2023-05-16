package org.bougainvilleas.ilj.designpattern.structural.flyweight;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlyWeightManagementTest {

  @Test
  void flyWeight() {
    FlyWeightManagement management = FlyWeightManagement.getInstance();
    FlyWeight flyWeight = management.get("嘿嘿");
    FlyWeight flyWeight2 = management.get("嘿嘿");
    assertSame(flyWeight, flyWeight2);
    flyWeight.use(new StateUnshared("乾乾"));
    flyWeight2.use(new StateUnshared("坤坤"));

    /**
     *  查看jdk源码 {@link Integer#valueOf(int)}。
     *  当使用{@link Integer#valueOf(int)}声明{@link Integer}时，
     *  如果值在[-128,127]范围内时，使用享元模式，返回的是同一个Integer对象，故x==x1为true。
     *  [-128,127]范围右边界可通过jvm参数配置 VM.getSavedProperty("java.lang.Integer.IntegerCache.high")。
     *  默认 Integer.IntegerCache.high=127
     *  i >= -128 && i <= Integer.IntegerCache.high
     *  Integer对象保存在 Integer.IntegerCache.cache[]
     *  数组索引index与Integer对象对应
     */
    Integer x = Integer.valueOf(127);
    Integer x1 = Integer.valueOf(127);
    assertSame(x, x1);

    Integer y = new Integer(127);
    Integer y1 = new Integer(127);

    assertEquals(x, y);
    assertEquals(y, y1);

    assertNotSame(x, y);
    assertNotSame(x, y1);
    assertNotSame(y, y1);

    Integer x2 = Integer.valueOf(128);
    Integer x3 = Integer.valueOf(128);
    assertNotSame(x2, x3);
    assertEquals(x2, x3);
  }
}