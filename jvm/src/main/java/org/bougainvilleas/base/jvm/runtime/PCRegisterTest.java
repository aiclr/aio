package org.bougainvilleas.base.jvm.runtime;

/**
 * Program Counter Register
 * PC寄存器
 *  javap -verbose PCRegisterTest.class
 *  javap -v PCRegisterTest.class
 *
 * [caddy@blackbox runtime]$ javap -verbose PCRegisterTest.class
 * Classfile /home/caddy/code/base4java/jvm/build/classes/java/main/org/bougainvillea/java/jvm/runtime/PCRegisterTest.class
 *   Last modified Jan 17, 2021; size 711 bytes
 *   MD5 checksum 529a80c06bd1c91fc0f1c731e5d0770e
 *   Compiled from "PCRegisterTest.java"
 * public class org.bougainvillea.java.jvm.runtime.PCRegisterTest
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #6.#26         // java/lang/Object."<init>":()V
 *    #2 = String             #27            // abc
 *    #3 = Fieldref           #28.#29        // java/lang/System.out:Ljava/io/PrintStream;
 *    #4 = Methodref          #30.#31        // java/io/PrintStream.println:(I)V
 *    #5 = Class              #32            // org/bougainvillea/java/jvm/runtime/PCRegisterTest
 *    #6 = Class              #33            // java/lang/Object
 *    #7 = Utf8               <init>
 *    #8 = Utf8               ()V
 *    #9 = Utf8               Code
 *   #10 = Utf8               LineNumberTable
 *   #11 = Utf8               LocalVariableTable
 *   #12 = Utf8               this
 *   #13 = Utf8               Lorg/bougainvillea/java/jvm/runtime/PCRegisterTest;
 *   #14 = Utf8               main
 *   #15 = Utf8               ([Ljava/lang/String;)V
 *   #16 = Utf8               args
 *   #17 = Utf8               [Ljava/lang/String;
 *   #18 = Utf8               i
 *   #19 = Utf8               I
 *   #20 = Utf8               j
 *   #21 = Utf8               k
 *   #22 = Utf8               s
 *   #23 = Utf8               Ljava/lang/String;
 *   #24 = Utf8               SourceFile
 *   #25 = Utf8               PCRegisterTest.java
 *   #26 = NameAndType        #7:#8          // "<init>":()V
 *   #27 = Utf8               abc
 *   #28 = Class              #34            // java/lang/System
 *   #29 = NameAndType        #35:#36        // out:Ljava/io/PrintStream;
 *   #30 = Class              #37            // java/io/PrintStream
 *   #31 = NameAndType        #38:#39        // println:(I)V
 *   #32 = Utf8               org/bougainvillea/java/jvm/runtime/PCRegisterTest
 *   #33 = Utf8               java/lang/Object
 *   #34 = Utf8               java/lang/System
 *   #35 = Utf8               out
 *   #36 = Utf8               Ljava/io/PrintStream;
 *   #37 = Utf8               java/io/PrintStream
 *   #38 = Utf8               println
 *   #39 = Utf8               (I)V
 * {
 *   public org.bougainvillea.java.jvm.runtime.PCRegisterTest();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 36: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lorg/bougainvillea/java/jvm/runtime/PCRegisterTest;
 *
 *   public static void main(java.lang.String[]);
 *     descriptor: ([Ljava/lang/String;)V
 *     flags: ACC_PUBLIC, ACC_STATIC
 *     Code:
 *       stack=2, locals=5, args_size=1
 *          0: bipush        10
 *          2: istore_1
 *          3: bipush        20
 *          5: istore_2
 *          6: iload_1
 *          7: iload_2
 *          8: iadd
 *          9: istore_3
 *         10: ldc           #2                  // String abc
 *         12: astore        4
 *         14: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         17: iload_1
 *         18: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
 *         21: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *         24: iload_3
 *         25: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
 *         28: return
 *       LineNumberTable:
 *         line 38: 0
 *         line 39: 3
 *         line 40: 6
 *         line 41: 10
 *         line 42: 14
 *         line 43: 21
 *         line 44: 28
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0      29     0  args   [Ljava/lang/String;
 *             3      26     1     i   I
 *             6      23     2     j   I
 *            10      19     3     k   I
 *            14      15     4     s   Ljava/lang/String;
 * }
 * SourceFile: "PCRegisterTest.java"
 *
 *  其中PC寄存器记录的就是 指令地址 0，2,3
 *    指令地址: 操作指令
 *          0: bipush        10
 *          2: istore_1
 *          3: bipush        20
 *
 *
 * eg：
 *  Code下有一行操作指令
 *         10: ldc           #2
 *
 *  #2  指向 Constant pool （常量池）内的
 *      #2 = String             #27
 *  #27 指向 Constant pool （常量池）内的
 *      #27 = Utf8               abc
 */
public class PCRegisterTest {
    public static void main(String[] args) {
        int i=10;
        int j=20;
        int k=i+j;
        String s="abc";
        System.out.println(i);
        System.out.println(k);
    }
}
