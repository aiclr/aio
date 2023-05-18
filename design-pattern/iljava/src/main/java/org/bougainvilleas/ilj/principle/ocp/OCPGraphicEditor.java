package org.bougainvilleas.ilj.principle.ocp;

/**
 * 开闭原则：对扩展开放，对修改关闭
 * <p>
 * 需求:提供者开放扩展，使用者关闭修改<br/>
 * 新增具体形状，提供者支持扩展，使用者不需要修改，符合开闭原则。
 * <p>
 * 提供者 {@link OCPShape}
 * 使用者 {@link OCPGraphicEditor}
 */
public class OCPGraphicEditor {
  public void drawShape(OCPShape shape) {
    shape.draw();
  }
}
