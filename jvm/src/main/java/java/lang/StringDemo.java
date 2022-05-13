package java.lang;

/**
 * 与java核心api 同名，同包
 * 此处定义的测试String 会引起IDEA编译String相关方法和构造器混乱，故注释掉
 * 
 * @author caddy
 */
public class StringDemo {

    /**
     * 静态代码块，正常的类 由AppClassLoader加载时会执行
     * 但是双亲委派机制，java.lang.String实际是由BootstrapClassloader加载
     *
     * 加载过程，
     * 首先AppClassloader获取到加载任务，
     * 向上委托
     * 交给父类加载器扩展类加载器ExtensionClassloader
     * 向上委托
     * 交给BootstrapClassloader，
     * 此时发现可以加载java核心类库的String，故加载核心类库的String
     * 并不会加载自定义的java.lang.String
     * 所以此处的静态代码块并不会执行
     */
    static{
        System.out.println("Hello 双亲委派机制");
    }


    /**
     * jvm内String类,通过双亲委派机制，实际是有BootstrapClassloader加载的java核心类库里的String
     * 核心类库内的String是没有main方法的
     * 所以此处的main方法会报ERROR
     *
     * Error: Main method not found in class java.lang.String
     */
    public static void main(StringDemo[] args) {
        System.out.println("自定义java.lang.java");
    }
}
