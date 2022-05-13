package org.bougainvilleas.base.jvm.runtime;

/**
 * 操作数栈
 *
 * byte、short、char、boolean都一int类型保存
 * bipush : byte 不超出int范围，使用int保存
 * sipush
 *
 *
 *
 * bipush：Push byte The immediate byte is sign-extended to an int value. That value is pushed onto the operand stack.
 * istore：Store int into local variable
 * iload：Load int from local variable
 * iadd：Both value1 and value2 must be of type int. The values are popped from the operand stack. The int result is value1 + value2. The result is pushed onto the operand stack
 * return：Return void from method
 *
 *  testAddOperation()
 *  0 bipush 15  int类型15压入operand stack
 *  2 istore_1   弹出pop operand stack栈顶的15,在local variables的索引为1的位置保存15（0位置保存的是this）
 *  3 bipush 8
 *  5 istore_2
 *  6 iload_1  将local variable index=1的int型数据压入operand stack
 *  7 iload_2  将local variable index=2的int型数据压入operand stack 此时栈顶是8
 *  8 iadd  依次弹出operand stack两个数据value1=8先出 和 value2=15后出，计算8+15=23,将23压入operand stack
 *  9 istore_3 弹出pop operand stack栈顶的23,在local variables的索引为3的位置保存23（此时local variables内数据为[this,15,8,23]）
 * 10 return 方法结束
 */
public class OperandStackTest {

    public void testAddOperation(){
        byte i=15;
        int j=8;
        int k=i+j;
    }

    /**
     *  0 bipush 8
     *  2 istore_1
     *  3 bipush 8
     *  5 istore_2
     *  6 bipush 8
     *  8 istore_3
     *  9 sipush 800
     * 12 istore 4
     * 14 iconst_1
     * 15 istore 5
     * 17 iconst_0
     * 18 istore 6
     * 20 iload 4     将返回值放到operand stack
     * 22 ireturn     返回int
     */
    public int testPush(){
        byte b=8;
        short s=8;
        int i = 8;
        int j = 800;
        boolean t=true;
        boolean f=false;
        return j;
    }

}
