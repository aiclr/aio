package org.bougainvilleas.ilj.designpattern.behavior.interpreter;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 * 解释器模式 Interpreter Pattern<br/>
 * 给定一个表达式,定义它的文法的一种表示,并定义一个解释器,使用该解释器来解释语言中的表达式
 * <p>
 * 在编译原理中,一个算术表达式通过词法分析器形成词法单元,<br/>
 * 而后这些词法单元,再通过语法分析器构建语法分析树,最终形成一颗抽象的语法分析树<br/>
 * 这里的词法分析器和语法分析器都可以看作是解释器
 * <p>
 * 场景:<br/>
 * 1.将一个需要解释执行的语言中的句子表示为一个抽象语法树<br/>
 * 2.一些重复出现的问题可以用一种简单的语言来表达<br/>
 * 3.一个简单语法需要解释的场景<br/>
 * 例子,编译器,运算表达式计算,正则表达式,机器人等
 * <p>
 * 抽象表达式,声明 一个抽象的解释操作,<br/>
 * 这个方法为抽象语法树中所有的节点所共享<br/>
 * 通过hashMap键值对,可以获取到变量的值
 */
public abstract class Expression {

  protected static final Logger log = Logger.getLogger(Expression.class.getSimpleName());

  /**
   * a+b-c
   * 解释公式和数值,key是公式,参数a,b,c
   *
   * @param var {a=10,b=20}
   * @return result
   */
  public abstract int interpreter(HashMap<String, Integer> var);
}
