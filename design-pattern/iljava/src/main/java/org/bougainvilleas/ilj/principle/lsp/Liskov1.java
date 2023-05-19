package org.bougainvilleas.ilj.principle.lsp;

import java.util.logging.Logger;

/**
 * 里氏替换原则  降低继承的耦合度
 */
public class Liskov1 {
  private static final Logger log = Logger.getLogger(Liskov.class.getSimpleName());

  public static void main(String[] args) {
    A2 a2 = new A2();
    log.info(Integer.toString(a2.func1(1,2)));

    B2 b2 = new B2(a2);
    log.info(Integer.toString(b2.func1(1,2)));
    log.info(Integer.toString(b2.func2(1,2)));
    log.info(Integer.toString(b2.func3(1,2)));

    System.err.println(b2.func(1,2));
  }

}

class Base{
  public int func(int a,int b){
    return a+b+b;
  }
}

class A2 extends Base{
  public int func1( int a,int b){
    return a+b;
  }
}

//使用组合替换继承关系
class B2 extends Base{

  A2 a2;
  public B2(A2 a2){
    this.a2=a2;
  }

  public int func1(int a, int b){
    return a-b;
  }

  public int func2(int a,int b){
    return func1(a,b)+9;
  }
  public int func3(int a,int b){
    return a2.func1(a,b)+9;
  }

}
