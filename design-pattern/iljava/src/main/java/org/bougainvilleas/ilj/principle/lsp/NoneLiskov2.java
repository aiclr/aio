package org.bougainvilleas.ilj.principle.lsp;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class NoneLiskov2 {
  public static void main(String[] args) {
    HashMap m = new HashMap();

    Base2 base2 = new Base2();
    base2.func(m);
    base2.put2(m);

    A3 a3 = new A3();
    //由于子类重载了父类方法，如果子类没有重写父类，则调用的是父类的方法
    a3.func(m);
    a3.put2(m);
  }

}

class Base2 {
  protected static final Logger log = Logger.getLogger(Base2.class.getSimpleName());

  public Integer func(HashMap map) {
    log.info("父类方法被执行");
    return 1;
  }

  public Map put2(HashMap map) {
    log.info("父类方法被执行");
    return map;
  }
}

class A3 extends Base2 {
  //覆写Overload不是重载Override

  //扩大前置条件
  public Integer func(Map map) {
    log.info("子类重载方法被执行");
    return 2;
  }

  //前置扩大，后置缩小
  public HashMap put2(Map map) {
    log.info("子类重载方法被执行");
    return (HashMap) map;
  }
  //重写则执行子类方法
//    @Override
//    public Number func(HashMap map) {
//        log.info("子类重写方法被执行");
//        return Integer.valueOf(2);
//    }

}