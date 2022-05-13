package org.bougainvilleas.base.jvm.runtime;

/**
 * i++
 * ++i
 */
public class OSTest {
    public static void main(String[] args) {
        new OSTest().test();
    }

    public void test(){


        /**
         *   0 bipush 10  入栈 10 is pushed onto the operand stack
         *   2 istore_1   出栈 Store 10 into local variable (数组，1位置，0位置为this)
         *   3 getstatic #5 <java/lang/System.out>
         *   6 iload_1    Load int from local variable into operand stack。10入栈(为了System.out.println才入栈的可以注释掉验证)
         *   7 iinc 1 by 1  ++i  第一个1为local variable数组下标.第二个1为增加的值 Increment local variable by constant 直接在local variables上操作
         *  10 invokevirtual #6 <java/io/PrintStream.println>   10 出栈
         */
        int i1=10;
        System.out.println(i1++);
//        System.out.println("10="+ (i1++));

        /**
         *  13 bipush 10
         *  15 istore_2
         *  16 getstatic #5 <java/lang/System.out>
         *  19 iinc 2 by 1  Increment local variable by constant 直接在local variables上操作
         *  22 iload_2
         *  23 invokevirtual #6 <java/io/PrintStream.println>
         */
        int i2=10;
        System.out.println(++i2);
//        System.out.println("11="+ (++i2));

        /**
         *  26 bipush 10
         *  28 istore_3
         *  29 iload_3
         *  30 iinc 3 by 1  Increment local variable by constant 直接在local variables上操作
         *  33 istore_3
         *  34 getstatic #5 <java/lang/System.out>
         *  37 iload_3
         *  38 invokevirtual #6 <java/io/PrintStream.println>
         */
        int i3=10;
        i3=i3++;
        System.out.println(i3);
//        System.out.println("10="+i3);

        /**
         * 41 bipush 10
         *  43 istore 4
         *  45 iinc 4 by 1  Increment local variable by constant 直接在local variables上操作
         *  48 iload 4
         *  50 istore 4
         *  52 getstatic #5 <java/lang/System.out>
         *  55 iload 4
         *  57 invokevirtual #6 <java/io/PrintStream.println>
         */
        int i4=10;
        i4=++i4;
        System.out.println(i4);
//        System.out.println("11="+i4);


        /**
         * 60 bipush 10
         *  62 istore 5
         *  64 iload 5
         *  66 iinc 5 by 1  Increment local variable by constant 直接在local variables上操作
         *  69 istore 6
         *  71 getstatic #5 <java/lang/System.out>
         *  74 iload 6
         *  76 invokevirtual #6 <java/io/PrintStream.println>
         */
        int i5=10;
        int i6=i5++;
        System.out.println(i6);
//        System.out.println("10="+i6);

        /**
         * 79 bipush 10
         *  81 istore 7
         *  83 iinc 7 by 1  Increment local variable by constant 直接在local variables上操作
         *  86 iload 7
         *  88 istore 8
         *  90 getstatic #5 <java/lang/System.out>
         *  93 iload 8
         *  95 invokevirtual #6 <java/io/PrintStream.println>
         */
        int i7=10;
        int i8=++i7;
        System.out.println(i8);
//        System.out.println("11="+i8);


        /**
         *  98 bipush 10
         * 100 istore 9
         * 102 iload 9           10
         * 104 iinc 9 by 1  Increment local variable by constant 直接在local variables上操作
         * 107 iinc 9 by 1  Increment local variable by constant 直接在local variables上操作
         * 110 iload 9           12
         * 112 iadd
         * 113 istore 10         22
         * 115 getstatic #5 <java/lang/System.out>
         * 118 iload 10
         * 120 invokevirtual #6 <java/io/PrintStream.println>
         */
        int i9=10;
        int i10=i9++ + ++i9;
        System.out.println(i10);
//        System.out.println("22="+i10);

    }
}
