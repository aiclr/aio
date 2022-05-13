package org.bougainvilleas.base.jvm.runtime.virtual;

/**
 * 虚方法 & 非虚方法
 *
 * 1.非虚方法（所有static方法+final/private 方法）通过invokespecial指令调用（final虽然是非虚方法，但是通过invokevirtual调用），
 *      不尝试做虚方法分派，对这个非虚方法的符号引用将转为对应的直接引用，即转为直接引用方法，在编译完成时就确定唯一的调用方法。
 * 2.虚方法通过invokevirtual指令调用，且会有分派。
 *      具体先根据编译期时方法接收者和方法参数的静态类型来分派，
 *      再在运行期根据只根据方法接收者的实际类型来分派，
 *      即Java语言是静态多分派，动态单分派类型的语言。
 *      注意: 在运行时，虚拟机只关心方法的实际接收者，不关心方法的参数，只根据方法接收者的实际类型来分派
 *
 *
 * 对非虚方法的调用，程序在编译时，就可以唯一确定一个可调用的版本，
 * 且这个方法在运行期不可改变，那么会在类加载的解析阶段，通过前面的指令1，指令2将对这个方法的符号引用转为对应的直接引用，即转为直接引用方法。
 * 在Java中，静态方法，final方法和private方法 都是不可在子类中重写的。所以他们都是非虚方法
 *
 * invokevirtual的语义是要尝试做虚方法分派，而invokespecial不尝试做虚方法分派。
 * 即invokevirtual调用的方法需要在运行时，根据目标对象的实际类型(代码2中为sub)来动态判断需要执行哪个方法。
 * 而invokespecial则只根据常量池中对应序号是哪个方法就执行哪个方法（即看静态类型）。
 * 这里有特殊的一点是，final方法是使用invokevirtual指令来调用的，但是由于它无法被覆盖（不存在其他版本），所以也无须对方法接收者进行多态选择，或者说多态选择的结果是唯一的。在Java语言规范中明确说明了final方法是一种非虚方法
 *
 * @author renqiankun
 */
public class SuperTest {

    public static void main(String[] args) {
        //Sub对象的虚方法表
        // exampleMethod指向父类的exampleMethod方法
        // interestingMethod为非虚方法直接指向父类的interestingMethod方法
        new Sub().exampleMethod();//Super's interestingMethod
        //Sub对象虚方法表
        // exampleMethod指向父类Super1的exampleMethod方法
        // interestingMethod指向自己的interestingMethod方法
        new Sub1().exampleMethod();//Sub's interestingMethod
    }
}

class Super {
    //private修饰为非虚方法，直接引用
    private void interestingMethod() {
        System.out.println("Super's interestingMethod");
    }

    /**
     * 0 aload_0
     * 1 invokespecial #5 <org/bougainvillea/java/jvm/runtime/virtual/Super.interestingMethod>
     * 4 return
     */
    void exampleMethod() {
        interestingMethod();//invokespecial非虚方法直接指向Super.interestingMethod
    }
}

class Sub extends Super {
    void interestingMethod() {
        System.out.println("Sub's interestingMethod");
    }
}

class Super1 {
    //可以重写的方法为虚方法
    void interestingMethod() {
        System.out.println("Super's interestingMethod");
    }

    /**
     * 0 aload_0
     * 1 invokevirtual #5 <org/bougainvillea/java/jvm/runtime/virtual/Super1.interestingMethod>
     * 4 return
     */
    void exampleMethod() {
        interestingMethod();//虚方法分派 invokevirtual
    }
}

class Sub1 extends Super1 {
    @Override
    void interestingMethod() {
        System.out.println("Sub's interestingMethod");
    }
}

/**
 * sayHello()方法是虚方法,通过invokevirtual指令调用。
 * 因为在编译期只看方法接收者和参数的静态类型，
 * 所以在编译完成后，产生了2条指令，选择了sayHello(Human)作为调用目标，
 * 并把这个方法的符号引用写到了main()方法里面的2条invokevirtual指令的参数中。
 * 然后在运行期，动态选择sd的实际类型，因为在这sd没有父类，所以不用考虑。
 * 还有另外一种解释是，
 *      所有依赖静态类型来定位方法执行版本的分派动作称为静态分派，静态分派的典型例子是方法重载
 */
class StaticSDispatch {
    static abstract class Human {}
    static class Man extends Human {}
    static class Woman extends Human {}

    //方法重载
    public void sayHello(Human guy) {
        System.out.println("hello,guy");
    }

    public void sayHello(Man man) {
        System.out.println("hello,man");
    }

    public void sayHello(Woman woman) {
        System.out.println("hello,woman");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Woman();
        StaticSDispatch sd = new StaticSDispatch();
        //只看man woman的类型为Human
        sd.sayHello(man);//hello,guy
        sd.sayHello(women);//hello,guy
    }
}

/**
 * sayHello()是虚方法，所以调用指令是invokevirtual.
 * 因为该方法没有参数，且方法接收者man/women的实际类型是Human，
 * 所以在编译期完成后会产生2条指令:Human.sayHello()；
 * 然后在动态运行时，只根据方法接收者的动态类型来动态分派，即会分派Man/Women的sayHello()方法
 */
class DynamicDispatch {
    static abstract class Human{
        protected abstract void sayHello();
    }

    static class Man extends Human{
        @Override
        protected void sayHello() {
            System.out.println("man say hello");
        }
    }

    static class Women extends Human {
        @Override
        protected void sayHello() {
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        DynamicDispatch dy =new DynamicDispatch();
        Human man =new Man();
        Human women =new Women();
        man.sayHello();
        women.sayHello();
        man =new Women();
        man.sayHello();
    }
}

class Dispatcher {

    static class QQ {
    }

    static class _360 {
    }

    public static class Father {
        public void hardChoice(QQ qq) {
            System.out.println("father choose qq");
        }

        public void hardChoice(_360 _360) {
            System.out.println("father choose 360");
        }
    }

    public static class Son extends Father{
        @Override
        public void hardChoice(QQ qq) {
            System.out.println("son choose qq");
        }

        @Override
        public void hardChoice(_360 _360) {
            System.out.println("son choose 360");
        }
    }

    public static void main(String[] args) {
        Father father = new Father();
        Father son = new Son();
        father.hardChoice(new _360());//father choose 360
        son.hardChoice(new QQ());//son choose qq

    }

}