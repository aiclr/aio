package org.bougainvilleas.resource.first;

import java.io.InputStream;
import java.util.Optional;

public class Client
{
    public static void main(String[] args)
    {
        //通过 bootLayer 引导层获取一个 module
        Optional<Module> resource_module = ModuleLayer.boot().findModule("resource_module");
        System.err.println("通过 bootLayer 引导层获取一个 module "+resource_module);
        resource_module.ifPresent(
                module ->
                {
                    try
                    {
                        //可以随时加载 来自 requires 模块的顶级资源
                        InputStream mod_top = module.getResourceAsStream("top_level_resource.txt");
                        System.err.println("可以随时加载 来自 requires 模块的顶级资源-->" + mod_top);
                        System.err.println(mod_top);
                        //META-INF 不是一个有效的包名称（带-号），所以可以访问该目录中的资源
                        InputStream mod_meta = module.getResourceAsStream("META-INF/meta.txt");
                        System.err.println("META-INF 不是一个有效的包名称（带-号），所以可以访问该目录中的资源 -->" + mod_meta);
                        //在默认情况下 来自其他模块的包中的资源是被封装的，此时会返回null
                        InputStream mod_pkg = module.getResourceAsStream("org/bougainvilleas/resource/second/resource_in_package.txt");
                        System.err.println("在默认情况下 来自 requires 模块的包中的资源是被封装的，此时会返回null-->" + mod_pkg);
                        //.class 文件例外 这些文件可以始终从另一个模块加载
                        InputStream mod_class = module.getResourceAsStream("org/bougainvilleas/resource/second/Client.class");
                        System.err.println(".class 文件例外 这些文件可以始终从另一个模块加载 -->" + mod_class);
                        //通过 Class::forName 可以获取一个 Class<?> 实例，但是通过该实例加载封装资源将返回null
                        Class<?> clazz = Class.forName("org.bougainvilleas.resource.second.Client");
                        System.err.println("通过 Class::forName 可以获取一个 Class<?> 实例-->" + clazz);
                        InputStream clazz_pkg = clazz.getResourceAsStream("resource_in_package.txt");
                        System.err.println("通过 Class::forName 可以获取一个 Class<?> 实例，但是通过该实例加载封装资源将返回null-->" + clazz_pkg);

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
        );
    }
}
