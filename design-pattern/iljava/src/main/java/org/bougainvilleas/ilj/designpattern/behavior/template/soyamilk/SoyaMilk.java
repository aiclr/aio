package org.bougainvilleas.ilj.designpattern.behavior.template.soyamilk;

import java.util.logging.Logger;

/**
 * 模板方法模式
 * 豆浆抽象类
 */
public abstract class SoyaMilk {
  protected static final Logger log = Logger.getLogger(SoyaMilk.class.getSimpleName());

  /**
   * 模板方法 final 修饰,防止子类重写模板方法
   */
  public final void make() {
    select();
    if (hook()) {
      addCondiments();
    }
    soak();
    beat();
  }

  private void select() {
    log.info("选黄豆");
  }

  /**
   * 添加配料
   */
  public abstract void addCondiments();

  private void soak() {
    log.info("浸泡");
  }

  private void beat() {
    log.info("放入豆浆机打碎");
  }

  /**
   * fixme 模板精髓 ----- 钩子函数
   */
  public boolean hook() {
    return true;
  }

}
