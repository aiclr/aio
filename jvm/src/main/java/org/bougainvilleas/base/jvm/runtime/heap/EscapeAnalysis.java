package org.bougainvilleas.base.jvm.runtime.heap;

/**
 * 逃逸分析
 *
 * 判断是否发生了逃逸分析，看new的对象是否有可能在方法外被调用
 */
public class EscapeAnalysis {

    public EscapeAnalysis obj;

    /**
     * 方法返回了EscapeAnalysis对象，EscapeAnalysis对象发生逃逸
     */
    public EscapeAnalysis getInstance(){
        return obj==null?new EscapeAnalysis():obj;
    }

    /**
     * 为成员变量obj赋值
     * 发生逃逸
     * 思考：如果obj声明为static，仍然会发生逃逸
     */
    public void setObj(){
        this.obj=new EscapeAnalysis();
    }

    /**
     * e对象作用域仅在当前方法中有效，没有发生逃逸
     */
    public void useEscapeAnalysis(){
        EscapeAnalysis e = new EscapeAnalysis();
    }

    /**
     * 引用成员变量的值，发生逃逸
     * e引用了getInstance()创建的对象
     * 该对象有可能在当前方法外被访问，所以发生逃逸
     */
    public void useEscapeAnalysis1(){
        EscapeAnalysis e = getInstance();
    }

}
