package org.bougainvilleas.resource.second;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Client
{
    public static void main(String[] args) throws IOException
    {
        Class<Client> clazz = Client.class;

        //Class getResource 读取一个资源，并解析相对于类所在包的名称（org.bougainvilleas.resource.second）
        InputStream cz_pkg = clazz.getResourceAsStream("resource_in_package.txt");
        System.err.println("Class getResource 读取一个资源，并解析相对于类所在包的名称（org.bougainvilleas.resource.second）-->"+cz_pkg);
        //读取顶级资源 必须以斜杠为前缀 以避免资源名称的相对解析
        URL top_level_resource = clazz.getResource("/top_level_resource.txt");
        System.err.println("读取顶级资源 必须以斜杠为前缀 以避免资源名称的相对解析URL="+top_level_resource);
        assert top_level_resource != null;
        InputStream cz_top = top_level_resource.openStream();
        System.err.println("读取顶级资源 必须以斜杠为前缀 以避免资源名称的相对解析 "+cz_top);
        //通过 Class 获取 java.lang.Module 实例 表示类来自哪个模块
        Module mod = clazz.getModule();
        //Module API 引入了从模块获取资源的新方法
        InputStream mod_pkg = mod.getResourceAsStream("org/bougainvilleas/resource/second/resource_in_package.txt");
        System.err.println("Module API 引入了从模块获取资源的新方法 "+mod_pkg);
        //getResourceAsStream 也适用于顶级资源
        //Module API 总是使用绝对名称因此顶级资源不需要前导斜杠
        InputStream mod_top_level = mod.getResourceAsStream("top_level_resource.txt");
        System.err.println("Module API 总是使用绝对名称因此顶级资源不需要前导斜杠 "+mod_top_level);

    }
}
