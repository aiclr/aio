package org.bougainvilleas.base.jvm.runtime;

/**
 * 动态链接 指向运行时常量池的方法引用
 * javap -verbose DynamicLinkingTest.class
 * Classfile /D:/develop/java/base4java/jvm/build/classes/java/main/org/bougainvillea/java/jvm/runtime/DynamicLinkingTest.class
 *   Last modified 2021-1-22; size 509 bytes
 *   MD5 checksum 8c50c368e92ea53032d225e1f314d942
 *   Compiled from "DynamicLinkingTest.java"
 * public class org.bougainvillea.java.jvm.runtime.DynamicLinkingTest
 *   minor version: 0
 *   major version: 52
 *   flags: ACC_PUBLIC, ACC_SUPER
 * Constant pool:
 *    #1 = Methodref          #4.#17         // java/lang/Object."<init>":()V
 *    #2 = Methodref          #3.#18         // org/bougainvillea/java/jvm/runtime/DynamicLinkingTest.methodA:()V
 *    #3 = Class              #19            // org/bougainvillea/java/jvm/runtime/DynamicLinkingTest
 *    #4 = Class              #20            // java/lang/Object ---------------------------所有类都继承了Object
 *    #5 = Utf8               <init>
 *    #6 = Utf8               ()V
 *    #7 = Utf8               Code
 *    #8 = Utf8               LineNumberTable
 *    #9 = Utf8               LocalVariableTable
 *   #10 = Utf8               this
 *   #11 = Utf8               Lorg/bougainvillea/java/jvm/runtime/DynamicLinkingTest;
 *   #12 = Utf8               methodA
 *   #13 = Utf8               methodB
 *   #14 = Utf8               ()I
 *   #15 = Utf8               SourceFile
 *   #16 = Utf8               DynamicLinkingTest.java
 *   #17 = NameAndType        #5:#6          // "<init>":()V
 *   #18 = NameAndType        #12:#6         // methodA:()V
 *   #19 = Utf8               org/bougainvillea/java/jvm/runtime/DynamicLinkingTest
 *   #20 = Utf8               java/lang/Object
 * {
 *   public org.bougainvillea.java.jvm.runtime.DynamicLinkingTest();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *          4: return
 *       LineNumberTable:
 *         line 7: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       5     0  this   Lorg/bougainvillea/java/jvm/runtime/DynamicLinkingTest;
 *
 *   public void methodA();
 *     descriptor: ()V
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=0, locals=1, args_size=1
 *          0: return
 *       LineNumberTable:
 *         line 11: 0
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       1     0  this   Lorg/bougainvillea/java/jvm/runtime/DynamicLinkingTest;
 *
 *   public int methodB();
 *     descriptor: ()I
 *     flags: ACC_PUBLIC
 *     Code:
 *       stack=1, locals=1, args_size=1
 *          0: aload_0
 *          1: invokevirtual #2                  // Method methodA:()V
 *          4: bipush        10
 *          6: ireturn
 *       LineNumberTable:
 *         line 24: 0
 *         line 25: 4
 *       LocalVariableTable:
 *         Start  Length  Slot  Name   Signature
 *             0       7     0  this   Lorg/bougainvillea/java/jvm/runtime/DynamicLinkingTest;
 * }
 * SourceFile: "DynamicLinkingTest.java"
 */
public class DynamicLinkingTest {

    public void methodA(){

    }

    /**
     * 0 aload_0
     * 1 invokevirtual #2 <org/bougainvillea/java/jvm/runtime/DynamicLinkingTest.methodA>
     * 4 bipush 10
     * 6 ireturn
     *
     * 根据invokevirtual #2
     * 1 invokevirtual #2
     * 在常量池Constant pool:找#2
     * #2 = Methodref          #3.#18
     *  找#3
     *      #3 = Class              #19
     *      找#19
     *      #19 = Utf8               org/bougainvillea/java/jvm/runtime/DynamicLinkingTest
     *  找#18
     *      #18 = NameAndType        #12:#6
     *      找#12
     *          #12 = Utf8               methodA
     *      找#6
     *          #6 = Utf8               ()V   代表返回值为void
     * 所以#3.#18  指向  org/bougainvillea/java/jvm/runtime/DynamicLinkingTest。methodA()
     */
    public int methodB(){
        methodA();
        return 10;
    }
}
