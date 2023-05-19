package org.bougainvilleas.ilj.principle.lsp;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * 子类前置条件范围小于父类前置条件
 * 违反里氏替换原则
 */
public class NoneLiskov3 {
  public C1 c;

  public void setC(C1 c) {
    this.c = c;
  }

  public static void main(String[] args) {
    //父类
    C1 c1 = new C1();
    //子类换父类
    C11 c11 = new C11();

    Map map=new HashMap();
    HashMap hashMap=new HashMap();
    // 由于子类方法重载，下方调用的全部是父类方法
    NoneLiskov3 liskov=new NoneLiskov3();
    liskov.setC(c1);
    liskov.c.get(map);
    liskov.setC(c11);
    liskov.c.get(map);
    liskov.setC(c1);
    liskov.c.get(hashMap);
    liskov.setC(c11);
    liskov.c.get(hashMap);

  }
}

class C1 {
  protected static final Logger log = Logger.getLogger(C1.class.getSimpleName());
  public int get(Map map) {
    log.info("C1");
    return 1;
  }
}

class C11 extends C1 {
  //不是方法重写
  public int get(HashMap hm) {
    log.info("C1");
    return 1;
  }
}