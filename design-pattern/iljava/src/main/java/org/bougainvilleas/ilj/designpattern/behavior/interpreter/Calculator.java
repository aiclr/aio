package org.bougainvilleas.ilj.designpattern.behavior.interpreter;

import java.util.HashMap;
import java.util.Stack;

/**
 * 上下文,包含解释器之外的全局信息
 * <p>
 * 通过客户端输入{@link Calculator}和 {@link ExpressionVar}信息
 */
public class Calculator {

  private Expression expression;

  /**
   * @param expStr a+b-c
   */
  public Calculator(String expStr) {
    Stack<Expression> stack=new Stack<>();
    //[a,+,b,-,c]
    char[] charArray=expStr.toCharArray();

    Expression left;
    Expression right;

    for(int i=0;i<charArray.length;i++){
      switch (charArray[i]){
        case '+':
          //遇到操作符号，从stack取出左操作数left  a
          left=stack.pop();
          //从expStr 取出右边表达式     b
          right=new ExpressionVar(String.valueOf(charArray[++i]));
          //将AddExpression对象压栈 stack
          stack.push(new ExpressionAdd(left,right));
          break;
        case '-':
          //从stack取出左操作表达式left  a
          left=stack.pop();
          //从expStr 取出右边表达式     c
          right=new ExpressionVar(String.valueOf(charArray[++i]));
          stack.push(new ExpressionSub(left,right));
          break;
        default:
          // 遇到操作数转换为表达式， 压入表达式栈
          stack.push(new ExpressionVar(String.valueOf(charArray[i])));
          break;
      }
    }
    //遍历完charArray stack就得到最后的Expression
    expression=stack.pop();
  }

  public int run(HashMap<String ,Integer> var){
    //将表达式和var绑定传递给expression的interpreter进行解释执行
    return expression.interpreter(var);
  }
}
