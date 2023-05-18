package org.bougainvilleas.ilj.principle.ocp;

import java.util.logging.Logger;

/**
 * 开闭原则：对扩展开放，对修改关闭。依赖倒转的演示代码也类似
 * <p>
 * 需求:提供者开放扩展，使用者关闭修改<br/>
 * 新增具体形状，提供者支持扩展，使用者不需要修改，符合开闭原则。
 * <p>
 * 提供者 {@link OCPShape}
 * 使用者 {@link OCPGraphicEditor}
 */
public abstract class OCPShape {

  protected static final Logger log = Logger.getLogger(OCPShape.class.getSimpleName());

  protected int m_type;

  public abstract void draw();

  protected OCPShape(int m_type) {
    this.m_type = m_type;
  }
}
