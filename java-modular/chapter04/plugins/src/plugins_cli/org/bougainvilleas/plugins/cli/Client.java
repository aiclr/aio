package org.bougainvilleas.plugins.cli;

import org.bougainvilleas.plugins.api.Api;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client
{
    public static void main(String[] args)
    {
        if (args.length < 1)
        {
            System.err.println("Please provide plugin directories");
            return;
        }
        System.err.println("Loading plugins form" + Arrays.toString(args));
        Stream<ModuleLayer> pluginLayers = Stream.of(args)
                .map(Client::createPluginLayer);
        pluginLayers.flatMap(layer -> toStream(ServiceLoader.load(layer, Api.class)))
                .forEach(Api::exec);
    }

    static <T> Stream<T> toStream(ServiceLoader<T> serviceLoader)
    {
        return serviceLoader.stream().map(ServiceLoader.Provider::get);
    }


    static ModuleLayer createPluginLayer(String dir)
    {
        //在构建 Configuration 时，需要提供一个 ModuleFinder 来定位各个模块
        ModuleFinder finder = ModuleFinder.of(Paths.get(dir));
        Set<ModuleReference> pluginModuleRefs = finder.findAll();
        //module names
        Set<String> pluginRoots = pluginModuleRefs.stream()
                .map(ref -> ref.descriptor().name())
                .filter(name -> name.equals("plugins_svca") || name.equals("plugins_svcb"))
                .collect(Collectors.toSet());
        //root layer 对于 boot layer 该过程是在启动时隐式完成的
        ModuleLayer parent = ModuleLayer.boot();
        //构建 ModuleLayer 之前 必须在该层中创建一个描述模块图的 Configuration
        //相对于 boot layer Configuration 已被解析，所以插件模块可以读取 plugins_api 模块
        Configuration cf = parent.configuration()
                .resolve(finder, ModuleFinder.of(), pluginRoots);
        //类加载器 插件层中的所有模块将使用相同类加载器定义
        ClassLoader scl = ClassLoader.getSystemClassLoader();
        //new layer
        ModuleLayer layer = parent.defineModulesWithOneLoader(cf, scl);
        return layer;
    }

    /**
     * 从多个父级 创建新层
     * @param parentLayer
     * @param dir
     */
    static ModuleLayer createLayer(List<ModuleLayer> parentLayer,String dir)
    {
        //在构建 Configuration 时，需要提供一个 ModuleFinder 来定位各个模块
        ModuleFinder finder = ModuleFinder.of(Paths.get(dir));
        Set<ModuleReference> pluginModuleRefs = finder.findAll();
        //module names
        Set<String> pluginRoots = pluginModuleRefs.stream()
                .map(ref -> ref.descriptor().name())
                .filter(name -> name.equals("plugins_svca") || name.equals("plugins_svcb"))
                .collect(Collectors.toSet());

        List<Configuration> parentConfigs = parentLayer.stream().map(ModuleLayer::configuration)
                .collect(Collectors.toList());
        /**
         * 接受多个配置作为父级
         */
        Configuration newConfig = Configuration.resolve(finder, parentConfigs, ModuleFinder.of(),pluginRoots);
        /**
         * 层结构也带有多个父级
         */
        ModuleLayer.Controller newLayer = ModuleLayer.defineModulesWithOneLoader(newConfig, parentLayer, ClassLoader.getSystemClassLoader());
        return newLayer.layer();
    }
}
