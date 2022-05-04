package org.bougainvilleas.layer.bootlayer;

import java.sql.Driver;

public class Client
{
    public static void main(String[] args)
    {
        Driver driver = null; // We reference java.sql.Driver to see 'java.sql' gets resolved
        ModuleLayer.boot()
                .modules()
                .forEach(m -> System.err.println(m.getName() + ", loader: " + m.getClassLoader()));
        System.err.println("System classloader: " + ClassLoader.getSystemClassLoader());
    }
}
