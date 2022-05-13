package org.bougainvilleas.base.jvm.runtime;

import java.util.Date;

/**
 * slot存储数据
 */
public class SlotTest {

    private int count=0;

    public static void main(String[] args) {
        new SlotTest().test1();
    }

    public SlotTest() {
        this.count = 2;
    }

    /**
     * 因为this变量不存在于静态方法的local variables中所以静态方法不能使用this
     */
    public static void testStatic(){
        Date date=new Date();
        int count=10;
        System.out.println(count);
//        this.count=1;
    }

    public void test1(){
        Date date=new Date();
        String name1="test";
        String info=test2(date,name1);
        System.out.println(date+name1);
    }

    /**
     * this 0
     * dateP 1
     * name2 2
     * weight 3&4
     * gender 5
     */
    public String test2(Date dateP, String name2) {
        dateP = null;
        name2 = "caddy";
        double weight = 140.5;//占据两个slot,3和4
        char gender = '男';//由于前一个参数占了两个slot所以此处index为5
        return dateP + name2;
    }

    //local variables 只有一个this
    public void test3() {
        count++;
    }

    //stack frame中的local variables中的slot可以重复利用
    //如果一个局部变量过了其作用域，那么在其作用域之后申明的新的局部变量就很可能会复用过期局部变量的slot,从而达到节省资源的目的、
    //this 0
    //a  1
    //b  2
    //c  2
    /**
     *  0 iconst_0
     *  1 istore_1   int a = 0;
             *  2 iconst_0
             *  3 istore_2   int b = 0;
 b的作用域    *  4 iload_1    a
             *  5 iconst_1   1
             *  6 iadd       a + 1
             *  7 istore_2   b = a + 1;
     *  8 iload_1    a
     *  9 iconst_1   1
     * 10 iadd       a + 1
     * 11 istore_2   c = a + 1
     * 12 return
     */
    public void test4() {
        int a = 0;
        {
            int b = 0;//当运行出花括号后，没有地方使用b，b被销毁回收
            b = a + 1;//b的作用域 start PC=4 Length=4 start pc 4到8 对应行号就是下面3行
        }
        int c = a + 1;//变量c使用已经销毁的变量b占据的slot位置
    }

    public void test5() {
        int a = 0;
        int b = 0;
        b = a + 1;
        int c = a + 1;
    }
}
