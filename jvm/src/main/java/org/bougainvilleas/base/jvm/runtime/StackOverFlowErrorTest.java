package org.bougainvilleas.base.jvm.runtime;

/**
 * stack可能出现的异常
 * JVM规范运行stack的大小是动态的或者是固定不变的
 *   固定大小的stack,每一个线程的JVM stack容量可以在线程创建的时候独立选定，如果线程请求分配的stack容量超过JVM stack允许的最大容量，JVM将抛出StackOverflowError异常
 *   动态扩展的stack，当尝试扩展的时候无法申请到足够的内存，或者在创建新的线程时没有足够的内存去创建对应的JVM stack JVM将抛出OutOfMemoryError异常
 */
public class StackOverFlowErrorTest {

    //Exception in thread "main" java.lang.StackOverflowError
    public static void main(String[] args) {
        main(args);
    }
}

/**
 * 设置栈大小
 * 参数 -Xss
 * 栈的大小直接决定函数调用的最大可达深度（例如递归，递归一次会有一个stack frame入栈，栈的大小会影响递归的次数）
 *
 * 参考jdk11的文档
 * tools-reference.pdf
 *
 * -Xss size
 * Sets the thread stack size (in bytes). Append the letter k or K to indicate KB, m or M to
 * indicate MB, and g or G to indicate GB. The default value depends on the platform:
 * • Linux/x64 (64-bit): 1024 KB
 * Chapter 2
 * java
 * 2-57
 * • macOS (64-bit): 1024 KB
 * • Oracle Solaris/x64 (64-bit): 1024 KB
 * • Windows: The default value depends on virtual memory
 * The following examples set the thread stack size to 1024 KB in different units:
 * -Xss1m
 * -Xss1024k
 * -Xss1048576
 * This option is similar to -XX:ThreadStackSize.
 *
 *
 * 不设置 linux 默认-Xss 1024k 输出10770
 * 设置 -Xss 512k(idea set VM Option “-Xss512K”) 输出4804
 */
class OOMTest{
    private static int a=1;
    public static void main(String[] args) {
        System.out.println(a);
        a++;
        main(args);
    }
}
