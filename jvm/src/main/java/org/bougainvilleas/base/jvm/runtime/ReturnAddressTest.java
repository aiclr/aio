package org.bougainvilleas.base.jvm.runtime;

import java.io.FileReader;
import java.io.IOException;
import java.util.Date;

/**
 * 方法返回地址
 */
public class ReturnAddressTest {

    public boolean methodBoolean(){
        return false;
    }
    public byte methodByte(){
        return 0;
    }
    public short methodShort(){
        return 0;
    }
    public char methodChar(){
        return 'a';
    }
    public int methodInt(){
        return 0;
    }
    public long methodLong(){
        return 0L;
    }
    public float methodFloat(){
        return 0.0f;
    }
    public double methodDouble(){
        return 0.0;
    }
    public String methodString(){
        return null;
    }
    public Date methodDate(){
        return null;
    }
    public void methodVoid(){
        return;//可写可不写
    }

    static {
        int i=10;
    }

    /**
     *   public void method2();
     *     descriptor: ()V
     *     flags: ACC_PUBLIC
     *     Code:
     *       stack=1, locals=2, args_size=1
     *          0: aload_0
     *          1: invokevirtual #2                  // Method methodVoid:()V
     *          4: aload_0
     *          5: invokevirtual #3                  // Method method1:()V
     *          8: goto          16
     *         11: astore_1
     *         12: aload_1
     *         13: invokevirtual #5                  // Method java/io/IOException.printStackTrace:()V
     *         16: return
     *       Exception table:
     *          from    to  target type
     *              4     8    11   Class java/io/IOException
     *       LineNumberTable:
     *         line 77: 0
     *         line 79: 4
     *         line 82: 8
     *         line 80: 11
     *         line 81: 12
     *         line 83: 16
     */
    public void method2(){
        methodVoid();
        try{
            method1();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void method1() throws IOException{
        FileReader fis=new FileReader("test.txt");
        char[] cBuffer=new char[1024];
        int len;
        while ((len=fis.read(cBuffer))!=1){
            String str=new String(cBuffer,0,len);
            System.out.println(str);
        }
        fis.close();
    }
}
