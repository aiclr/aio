package org.bougainvilleas.plugins.cli;

import org.bougainvilleas.plugins.api.Api;

import java.lang.module.Configuration;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client
{
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Please provide plugin directories");
            return;
        }
        System.err.println("Loading plugins form" + Arrays.toString(args));
        Stream<ModuleLayer> pluginLayers = Stream.of(args)
                .map(Client::createPluginLayer);
        pluginLayers.flatMap(layer -> toStream(ServiceLoader.load(layer, Api.class)))
                .forEach(Api::exec);
    }

    static <T> Stream<T> toStream(ServiceLoader<T> serviceLoader) {
        return serviceLoader.stream().map(ServiceLoader.Provider::get);
    }


    static ModuleLayer createPluginLayer(String dir) {
        ModuleFinder finder = ModuleFinder.of(Paths.get(dir));
        Set<ModuleReference> pluginModuleRefs = finder.findAll();
        //module names
        Set<String> pluginRoots = pluginModuleRefs.stream()
                .map(ref -> ref.descriptor().name())
                .filter(name -> name.equals("plugins_svca") || name.equals("plugins_svcb"))
                .collect(Collectors.toSet());
        //root layer
        ModuleLayer parent = ModuleLayer.boot();
        Configuration cf = parent.configuration()
                .resolve(finder, ModuleFinder.of(), pluginRoots);
        ClassLoader scl = ClassLoader.getSystemClassLoader();
        //new layer
        ModuleLayer layer = parent.defineModulesWithOneLoader(cf, scl);
        return layer;
    }
}
