package org.bougainvilleas.ilj.designpattern.behavior.interpreter;

import java.util.HashMap;

/**
 * 减法 解释器
 */
public class ExpressionSub extends ExpressionSymbol {
  public ExpressionSub(Expression left, Expression right) {
    super(left, right);
  }

  /**
   * 处理减法
   *
   * @param var {a=10,b=20}
   * @return 结果
   */
  @Override
  public int interpreter(HashMap<String, Integer> var) {
    /**
     * 实际调用的是{@link ExpressionVar#interpreter(HashMap)}
     */
    return super.left.interpreter(var) - super.right.interpreter(var);
  }
}
