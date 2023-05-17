package org.bougainvilleas.ilj.designpattern.behavior.visitor;

/**
 * 具体访问者,实现每个由Visitor声明的操作,是每个操作实现的部分
 * <p>
 * 双分派---为了解耦<br/>
 * 首先在客户端程序,将<b>具体状态</b>作为参数传递到{@link ElementWoman}中---第一次分派<br/>
 * 然后{@link ElementWoman}调用具体方法{@link Visitor#womanVisitor(ElementWoman)},同时将自己作为参数传入---第二次分派<br/>
 * <p>
 * 不管类怎么变化,我们都能找到期望的方法运行<br/>
 * 双分派意味着得到执行的操作取决于请求的种类和两个接受者的类型
 * <p>
 * 如果需要新增一个{@link Visitor}子类,{@link ElementMan}和{@link ElementWoman}不用做任何修改<br/>
 * 客户端可直接调用---ocp,解耦
 * <p>
 * {@link Element} 接收一个访问者对象
 */
public abstract class Element {
  abstract void accept(Visitor visitor);
}
