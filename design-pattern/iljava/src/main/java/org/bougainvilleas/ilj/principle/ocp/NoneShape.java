package org.bougainvilleas.ilj.principle.ocp;

/**
 * 开闭原则：对扩展开放，对修改关闭
 * <p>
 * 需求:提供者开放扩展，使用者关闭修改<br/>
 * 新增具体形状，提供者支持扩展，但是使用者也要修改，不符合开闭原则。
 * <p>
 * 提供者 {@link NoneShape}
 * 使用者 {@link NoneGraphicEditor}
 */
public class NoneShape {
  protected int m_type;

  public NoneShape(int m_type) {
    this.m_type = m_type;
  }
}
