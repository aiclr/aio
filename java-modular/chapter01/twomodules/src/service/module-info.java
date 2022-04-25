/**
 * @author renqiankun
 * 模块名称必须是唯一的：应用程序只能具有一个给定名称的模块。
 * 在Java中，通常使用反向DNS符号来确保包名称全局唯一。(反向dns hello.example.com 反转即为包名  com.example.hello 可以防止包名重复)
 * 可以对模块应用相同的方法。
 * 例如，可以将helloworld模块重命名为com.javamodularity.helloworld，但这样做会导致模块名称过长且有些笨重
 */
module service
{
    exports org.bougainvilleas.service;
}