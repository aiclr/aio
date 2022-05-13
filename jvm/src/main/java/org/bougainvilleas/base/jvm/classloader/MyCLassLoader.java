package org.bougainvilleas.base.jvm.classloader;

import java.io.FileNotFoundException;

/**
 * 自定义ClassLoader 模型
 *
 * @author renqiankun
 */
public class MyCLassLoader extends ClassLoader {


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = getClass(name);
            if (bytes == null) {
                throw new FileNotFoundException();
            } else {
                return defineClass(name, bytes, 0, bytes.length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        throw new ClassNotFoundException();
    }

    /**
     * 类似从本地获取class文件字节流
     * 将源码加密，此处可以加入解密逻辑
     * @param name
     * @return .class文件字节流
     * @throws FileNotFoundException
     */
    private byte[] getClass(String name) throws FileNotFoundException {

        return null;
    }

    public static void main(String[] args) {
        MyCLassLoader myCLassLoader = new MyCLassLoader();
        try {
            Class<?> one = Class.forName("One", true, myCLassLoader);
            Object obj=one.newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch (ClassNotFoundException |IllegalAccessException |InstantiationException e) {
            e.printStackTrace();
        }
    }
}
