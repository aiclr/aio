package org.bougainvilleas.ilj.designpattern.behavior.interpreter;

import java.util.HashMap;

/**
 * 变量的解释器<br/>
 * 终结符表达式,实现与文法中的终结符相关的解释操作
 */
public class ExpressionVar extends Expression {

  //key=a.key=b.key=c
  private String key;

  public ExpressionVar(String key) {
    this.key = key;
  }

  /**
   * 根据变量名,返回对应的值
   *
   * @param var {a=10,b=20}
   * @return result
   */
  @Override
  public int interpreter(HashMap<String, Integer> var) {
    return var.get(key);
  }
}
