package org.bougainvilleas.base.jvm;

/**
 * jclasslib 分析代码
 *
 * @author renqiankun
 */
public class HelloJvm {

    private final static int PI=3;

    //Linking阶段的Prepare时为a分配内存 赋值为0
    //initial初始化时才会赋值为10，执行static代码块再赋值20，最终a=20
    private static int a=10;

    static {
        a=20;
        b=30;
        System.out.println(a);
//        System.out.println(b);
    }
    //Linking的prepare阶段b=0，initial时，按顺序先赋值30，再赋值11，最终结果时11
    private static int b=11;

    public static void main(String[] args) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(B.b1);
    }

    //Initialization阶段的<init>()对应构造器
    public HelloJvm() {
        a=22;
        b=33;
    }

    //父类<clinit>()执行完
    static class A{
        static int a1=10;
        static {
            a1=20;
        }
    }
    //子类<clinit>()才能执行
    //先获取父类a1
    //0 getstatic #2 <org/bougainvillea/java/jvm/HelloJvm$B.a1>
    //进行赋值
    //3 putstatic #3 <org/bougainvillea/java/jvm/HelloJvm$B.b1>
    static class B extends A{
        static int b1=a1;
    }


}
