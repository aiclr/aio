package org.bougainvilleas.module.first;

import java.lang.module.ModuleDescriptor;
import java.util.Arrays;
import java.util.Set;

public class Client
{
    public static void main(String[] args)
    {
        System.err.println("Module module=Client.class.getModule();");
        Module module=Client.class.getModule();
        String moduleName = module.getName();
        System.err.println("Module::getName 获取 module-info.java 中定义的名称："+moduleName);

        System.err.println("获取模块上的注释");
        Arrays.stream(module.getAnnotations()).forEach(System.err::println);

        System.err.println("列出模块中的所有包");
        Set<String> packages = module.getPackages();
        packages.forEach(System.err::println);

        System.err.println("ModuleDescriptor descriptor = module.getDescriptor()");
        ModuleDescriptor descriptor = module.getDescriptor();
        String moduleName1=descriptor.name();
        System.err.println("ModuleDescriptor::name 获取 module-info.java 中定义的名称："+moduleName1);

        System.err.println("列出模块中的所有包");
        Set<String> packages1 = descriptor.packages();
        packages1.forEach(System.err::println);

        System.err.println("列出模块中的 module-info.java exports");
        Set<ModuleDescriptor.Exports> exports = descriptor.exports();
        exports.forEach(System.err::println);

        System.err.println("列出模块中的使用的服务 module-info.java uses");
        Set<String> uses = descriptor.uses();
        uses.forEach(System.err::println);
    }
}
