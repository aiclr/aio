package org.bougainvilleas.base.jvm.runtime;

/**
 * debug 时 可以看到Debugger窗口下有个Frames 可以看到各个方法
 *
 * 抛出异常导致栈帧被弹出
 *  1.不使用try-catch时，不会执行  System.out.println("main 正常结束");
 *      method1出现异常弹出当前栈帧，此时当前栈帧为main,main未处理异常也弹出当前栈帧，
 *      此时JVM内线程只剩守护线程，操作系统终止JVM
 *  2.使用try-catch捕获异常 会执行  System.out.println("main 正常结束"); 此时为正常函数返回
 *
 */
public class StackFrameTest {

    public static void main(String[] args) {

//        new StackFrameTest().method1();

        try{
            new StackFrameTest().method1();
        } catch (Exception e){

        }
        System.out.println("main 正常结束");
    }

    public void method1(){
        System.out.println("method1 start");
        method2();
        System.out.println("method1 end");
        System.out.println(10/0);
    }

    public int method2(){
        System.out.println("method2 start");
        int i=10;
        int m=(int)method3();
        System.out.println("method2 will end");//return 后才结束
        return i+m;
    }

    public double method3(){
        System.out.println("method3 start");
        double j=20.0;
        System.out.println("method3 will end");
        return j;
    }
}
