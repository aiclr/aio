package org.bougainvilleas.ilj.designpattern.behavior.visitor;

/**
 * 具体访问者
 */
public class ElementWoman extends Element {
  @Override
  void accept(Visitor visitor) {
    visitor.womanVisitor(this);
  }
}
