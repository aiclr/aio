package org.bougainvilleas.module.anno;

import java.util.Arrays;

public class Client
{
    public static void main(String[] args)
    {
        System.err.println("获取模块上的注释");
        Module module = Client.class.getModule();
        Arrays.stream(module.getAnnotations()).forEach(System.err::println);
    }
}
