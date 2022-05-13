package org.bougainvilleas.base.jvm.classloader;

import com.sun.net.ssl.internal.ssl.Provider;
import sun.misc.Launcher;
import sun.security.util.CurveDB;

import java.net.URL;

/**
 * 类加载器
 * 用户自定义类 默认使用AppClassLoader应用类加载器（系统类加载器）
 * java核心类库 使用 c和C++实现的BootstrapClassLoader引导类加载器
 *
 * @author renqiankun
 */
public class ClassLoaderTest {

    public static void main(String[] args) {

        ClassLoader systemClassLoader=ClassLoader.getSystemClassLoader();
        //sun.misc.Launcher$AppClassLoader 系统类加载器java实现
        System.out.println(systemClassLoader);

        ClassLoader extClassLoader = systemClassLoader.getParent();
        //sun.misc.Launcher$ExtClassLoader 扩展类加载器java实现
        System.out.println(extClassLoader);

        ClassLoader bootstrapClassloader = extClassLoader.getParent();
        //null 引导类加载器（由c和c++实现）
        System.out.println(bootstrapClassloader);


        //用户自定义类型的类加载器默认式系统类加载器-AppClassLoader
        ClassLoader classLoader = ClassLoaderTest.class.getClassLoader();
        System.out.println(classLoader);
        //系统类加载器只有一个
        System.out.println(classLoader==systemClassLoader);

        //java核心类库都是使用 BootstrapClassloader引导类加载器进行加载
        ClassLoader classLoader1 = String.class.getClassLoader();
        System.out.println(classLoader1);


        System.out.println("#############启动类加载器/引导类加载器########");
        /**
         * 获取BootstrapClassloader能够加载的api的路径
         * 下面的jar都是由BootstrapClassloader加载
         * file:/usr/lib/jvm/jdk1.8.0_321/jre/lib/resources.jar
         * file:/usr/lib/jvm/jdk1.8.0_321/jre/lib/rt.jar
         * file:/usr/lib/jvm/jdk1.8.0_321/jre/lib/sunrsasign.jar
         * file:/usr/lib/jvm/jdk1.8.0_321/jre/lib/jsse.jar
         * file:/usr/lib/jvm/jdk1.8.0_321/jre/lib/jce.jar
         * file:/usr/lib/jvm/jdk1.8.0_321/jre/lib/charsets.jar
         * file:/usr/lib/jvm/jdk1.8.0_321/jre/lib/jfr.jar
         * file:/usr/lib/jvm/jdk1.8.0_321/jre/classes
         */
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urLs) {
            System.out.println(url.toExternalForm());
        }
        //file:/usr/lib/jvm/jdk1.8.0_321/jre/lib/jsse.jar包内的Provider
        //可以解压jar，找到Provider.class，确认有此类
        ClassLoader classLoader2 = Provider.class.getClassLoader();
        //null===>类加载器为BootstrapClassloader
        System.out.println(classLoader2);
        /**
         * 下面目录内的jar包都由ExtClassloader加载
         * windows
         * C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext
         * C:\WINDOWS\Sun\Java\lib\ext
         *
         * linux
         * /usr/lib/jvm/jdk1.8.0_321/jre/lib/ext
         * /usr/java/packages/lib/ext
         */
        System.out.println("#########扩展类加载器###########");
        String extDirs = System.getProperty("java.ext.dirs");
        // MARK windows ; 分号分割
        System.out.println("windows ; 分号分割");
        for (String path : extDirs.split(";") ) {
            System.out.println(path);
        }
        System.out.println("linux : 分号分割");
        // MARK linux : 分号分割
        for (String path : extDirs.split(":") ) {
            System.out.println(path);
        }
        //解压 C:\Program Files\Java\jdk1.8.0_191\jre\lib\ext\sunec.jar
        // 从上面路径选择一个类，验证类加载器 sun.security.ec.CurveDB;
        ClassLoader classLoader3 = CurveDB.class.getClassLoader();
        //sun.misc.Launcher$ExtClassLoader@15db97
        System.out.println(classLoader3);

    }
}
