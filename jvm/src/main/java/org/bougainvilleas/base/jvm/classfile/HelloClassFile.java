package org.bougainvilleas.base.jvm.classfile;


public class HelloClassFile extends HelloClassSuper implements HelloClassI{
    public static void main(String[] args) {

        long l=100000000L;

        float f=l;
        System.out.println(f);
    }

}

class HelloClassSuper{

    public final static float a=1.0f;
//    public final static Integer a1=128;



}

interface HelloClassI{

}