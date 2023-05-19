package org.bougainvilleas.ilj.principle.lsp;

import java.util.logging.Logger;

/**
 * 里氏替换原则面向对象设计的基本原则之一。
 * 里氏替换原则中说，任何基类可以出现的地方，子类一定可以出现。
 * LSP是继承复用的基石，只有当衍生类可以替换掉基类，软件单位的功能不受到影响时，
 * 基类才能真正被复用，而衍生类也能够在基类的基础上增加新的行为。
 * A和B耦合度高
 */
public class Liskov {
  protected static final Logger log = Logger.getLogger(Liskov.class.getSimpleName());

  private A a;

  public void setA(A a) {
    this.a = a;
  }

  public static void main(String[] args) {
    Liskov liskov = new Liskov();
    A a = new A();
    B b = new B();
    liskov.setA(a);
    log.info(Integer.toString(liskov.a.func1(2,1)));
    liskov.setA(b);
    log.info(Integer.toString(liskov.a.func1(2,1)));

    log.info(Integer.toString(b.func2(2, 1)));
  }
}

class A {
  public int func1(int a, int b) {
    return a + b;
  }
}

class B extends A {

  @Override
  public int func1(int a, int b) {
    return a - b;
  }

  public int func2(int a, int b) {
    return func1(a, b) + 9;
  }

}