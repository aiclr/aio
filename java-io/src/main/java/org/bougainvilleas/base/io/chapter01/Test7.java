package org.bougainvilleas.base.io.chapter01;

import java.nio.CharBuffer;

/**
 * flip(),写后从头读，防止之前设置的position影响当前需要读取的位置
 * clear()，重头开始写，防止之前设置的position，limit，影响当前的写入
 * @author renqiankun
 */
public class Test7 {
    public static void main(String[] args) {
        char[] chars=new char[]{'a','b','c'};

        CharBuffer charBuffer = CharBuffer.wrap(chars);
        charBuffer.position(1);
        charBuffer.mark();
        charBuffer.put('s');
        System.err.println(charBuffer.position());
        System.err.println(charBuffer.get());//c,position=2
        charBuffer.flip();
        System.err.println(charBuffer.position());
        System.err.println(charBuffer.get());//a,position=0

        CharBuffer charBuffer1 = CharBuffer.wrap(chars);
        charBuffer1.position(1);
        charBuffer1.put('s');//asc
        Test3.display(charBuffer1);
        charBuffer1.clear();
        charBuffer1.put('z');//zsc::sc是原始数据，不会被清除
        Test3.display(charBuffer1);
    }
}

//bug演示
class Test7_1{
    public static void main(String[] args) {
        errorFlip();
        okFlip();
        errorClear();
        okClear();
    }

    public static void errorFlip(){
        char[] chars=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s'};
        CharBuffer charBuffer= CharBuffer.wrap(chars);
        System.err.println(charBuffer.position());
        charBuffer.put("我是中国人我在中华人民共和国");
        //不用flip(),会多输出opqrs
        Test3.display(charBuffer);
    }
    public static void okFlip(){
        char[] chars=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s'};
        CharBuffer charBuffer= CharBuffer.wrap(chars);
        System.err.println(charBuffer.position());
        charBuffer.put("我是中国人我在中华人民共和国");
        charBuffer.flip();
        Test3.display(charBuffer);
    }
    public static void errorClear(){
        char[] chars=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s'};
        CharBuffer charBuffer= CharBuffer.wrap(chars);
        System.err.println(charBuffer.position());
        charBuffer.put("我是中国人我在中华人民共和国");
        //重新写入
        charBuffer.put("他是美国人");
        //不clear，
        Test3.display(charBuffer);
    }
    public static void okClear(){
        char[] chars=new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s'};
        CharBuffer charBuffer= CharBuffer.wrap(chars);
        System.err.println(charBuffer.position());
        charBuffer.put("我是中国人我在中华人民共和国");
        charBuffer.clear();
        charBuffer.put("他是美国人");
        charBuffer.flip();
        Test3.display(charBuffer);
    }
}