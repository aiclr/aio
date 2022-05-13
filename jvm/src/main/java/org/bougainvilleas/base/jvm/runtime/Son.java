package org.bougainvilleas.base.jvm.runtime;

/**
 * invokevirtual:调用所有虚方法（final修饰的方法除外，final修饰的方法也使用invokevirtual指令）
 * invokeinterface:调用接口方法
 * invokestatic:调用静态方法，ClassLoaderSubSystem.Linking.Resolve时(解析阶段)确定唯一方法版本
 * invokespecial:调用<init>方法、私有方法、父类方法，解析阶段确定唯一方法版本
 *
 * invokedynamic:动态解析出需要调用的方法，然后执行
 *
 *
 */
public class Son extends Father {
    /**
     * 0 aload_0
     * 1 invokespecial #1 <org/bougainvillea/java/jvm/runtime/Father.<init>>
     * 4 return
     */
    public Son() {
        super();
    }

    /**
     * 0 aload_0
     * 1 invokespecial #2 <org/bougainvillea/java/jvm/runtime/Son.<init>>
     * 4 return
     */
    public Son(int age) {
        this();
    }

    public static void showStatic(String str) {
        System.out.println("son" + str);
    }

    private void showPrivate(String str) {
        System.out.println("son private" + str);
    }

    /**
     *  0 ldc #11 < bougainvillea>
     *  2 invokestatic #12 <org/bougainvillea/java/jvm/runtime/Son.showStatic>
     *  5 ldc #13 <error>
     *  7 invokestatic #14 <org/bougainvillea/java/jvm/runtime/Father.showStatic>
     * 10 aload_0
     * 11 ldc #15 <hi>
     * 13 invokespecial #16 <org/bougainvillea/java/jvm/runtime/Son.showPrivate>
     * 16 aload_0
     * 17 invokespecial #17 <org/bougainvillea/java/jvm/runtime/Father.showCommon>
     * 20 aload_0
     * 21 invokevirtual #18 <org/bougainvillea/java/jvm/runtime/Son.showFinal>
     * 24 aload_0
     * 25 invokespecial #19 <org/bougainvillea/java/jvm/runtime/Father.showFinal>
     * 28 aload_0
     * 29 invokevirtual #20 <org/bougainvillea/java/jvm/runtime/Son.showCommon>
     * 32 aload_0
     * 33 invokevirtual #21 <org/bougainvillea/java/jvm/runtime/Son.info>
     * 36 aconst_null
     * 37 astore_1
     * 38 aload_1
     * 39 invokeinterface #22 <org/bougainvillea/java/jvm/runtime/MethodInterface.methodA> count 1
     * 44 return
     */
    public void show() {
        //invokestatic
        showStatic(" bougainvillea");
        //invokestatic
        super.showStatic("error");
        //invokespecial
        showPrivate("hi");
        //invokespecial
        super.showCommon();
        //invokevirtual
        showFinal();//因为此方法声明为final，不能赋予子类重写，所以也认为此方法时非虚方法
        //invokespecial
        super.showFinal();
        //invokevirtual
        showCommon();
        //invokevirtual
        info();
        MethodInterface in = null;
        //invokeinterface
        in.methodA();

    }

    public void info() {

    }

    public void display(Father f) {
        f.showCommon();
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.show();
    }
}

class Father {
    public Father() {
        System.out.println("fater 构造器");
    }

    public static void showStatic(String str) {
        System.out.println("father" + str);
    }

    public final void showFinal() {
        System.out.println("father show final");
    }

    public void showCommon() {
        System.out.println("father 普通方法");
    }
}

interface MethodInterface {
    void methodA();
}