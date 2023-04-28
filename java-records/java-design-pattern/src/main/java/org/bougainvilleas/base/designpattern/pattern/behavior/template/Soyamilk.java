package org.bougainvilleas.base.designpattern.pattern.behavior.template;

import java.util.Arrays;

/**
 * 抽象类,豆浆
 */
public abstract class Soyamilk {

  /**
   * 模板方法 final 修饰,防止子类重写模板方法
   */
  final void make() {
    select();
    if (hook()) {
      addCondiments();
    }
    soak();
    beat();
  }

  /**
   * 选材
   */
  private void select() {
    System.err.println("选黄豆");
  }

  /**
   * 添加配料
   */
  abstract void addCondiments();

  /**
   * 浸泡
   */
  private void soak() {
    System.err.println("浸泡");
  }

  /**
   * 打豆浆
   */
  private void beat() {
    System.err.println("放入豆浆机打碎");
  }

  /**
   * 模板精髓 -----钩子
   *
   * @return
   */
  public boolean hook() {
    return true;
  }

}


/**
 * 花生豆浆
 */
class PeanutSoyamilk extends Soyamilk {
  @Override
  void addCondiments() {
    System.err.println("加入花生");
  }
}

/**
 * 红豆豆浆
 */
class RedBeanSoyamilk extends Soyamilk {
  @Override
  void addCondiments() {
    System.err.println("加入红豆");
  }
}

/**
 * 纯豆浆 钩子
 */
class PureSoyamilk extends Soyamilk {

  @Override
  void addCondiments() {
    //空方法
  }

  @Override
  public boolean hook() {
    return false;
  }
}

/**
 * Lambda 版本
 */
interface Handle {
  void handle();
}


class SoyamilkLambda {
  private Handle select;
  private boolean hook;
  private Handle addCondiments;
  private Handle soak;
  private Handle beat;

  public SoyamilkLambda(Handle select, boolean hook, Handle addCondiments, Handle soak, Handle beat) {
    this.select = select;
    this.addCondiments = addCondiments;
    this.soak = soak;
    this.beat = beat;
    this.hook = hook;
  }

  public void make() {
    select.handle();
    if (hook)
      addCondiments.handle();
    soak.handle();
    beat.handle();
  }
}

class Condiments {
  public void select() {
    System.err.println("选黄豆");
  }

  public void soak() {
    System.err.println("浸泡");
  }

  public void beat() {
    System.err.println("放入豆浆机打碎");
  }


  public void peanut() {
    System.err.println("加入花生");
  }

  public void redBean() {
    System.err.println("加入红豆");
  }
}

class SoyamilkLambdaPro {
  private Handle[] handles;

  public SoyamilkLambdaPro(Handle ... handles) {
    this.handles=handles;
  }

  public void make() {
    Arrays.stream(handles).forEach(Handle::handle);
  }
}

