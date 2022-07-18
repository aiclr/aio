package org.bougainvilleas.ilg.study.chapter07.extension

import groovy.json.JsonSlurper

/**
 * 扩展模块类
 * 在编译时向现有类添加实例方法或静态方法，并在运行时应用他们
 * 1. 添加的方法必须定义在扩展模块类中（扩展方法必须 static）
 * 2. 需要manifest放一些描述信息，告诉groovy编译器要查找的扩展模块
 *
 */
class TempExtension {
    /**
     * 在清单声明中会指明 要添加的这个方法是实例方法 还是静态方法
     * @param self 说明该方法被添加到哪个类上 城市编号 隐含地包含在实例中
     * @return 当前气温
     */
    public static double getTemp(String self){
        def url="http://www.weather.com.cn/data/sk/${self}.html".toURL()
        def data = new JsonSlurper().parseText(url.readLines("UTF-8")[0])
        data.weatherinfo.temp as double
    }
}
