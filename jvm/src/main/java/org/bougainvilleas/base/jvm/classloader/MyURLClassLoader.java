package org.bougainvilleas.base.jvm.classloader;

import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandlerFactory;

/**
 * 继承URLClassLoader 实现Classloader
 * @author renqiankun
 */
public class MyURLClassLoader extends URLClassLoader {

    public MyURLClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }

    public MyURLClassLoader(URL[] urls) {
        super(urls);
    }

    public MyURLClassLoader(URL[] urls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(urls, parent, factory);
    }
}
