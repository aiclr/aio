package org.bougainvilleas.ilg.study.chapter11

/**
 * 运行时查询一个对象的方法和属性
 * 以确定改对象是否支持某一特定行为
 * 不仅可以向类添加行为，还可以向类的一些实例添加行为
 *
 * MetaClass 扩展了 MetaObjectProtocol
 * 可以使用 MetaObjectProtocol 的 getMetaMethod()来获取一个元方法 MetaMethod
 * getStaticMetaMethod() 获取静态方法
 *
 * getMetaMethods() 获得重载方法列表
 * getStaticMetaMethods() 获得重载方法列表 [没有了]
 *
 * 获取元属性 MetaProperty
 * getMetaProperty()
 * getStaticMetaProperty()
 *
 * 检查是否存在
 * 使用 respondsTo() 检查方法
 * 使用 hasProperty() 检查属性
 */
str="hello"
methodName="toUpperCase"
staticMethodName="format"
property='CASE_INSENSITIVE_ORDER'

println "Does String responds to ${methodName}()?"
println String.metaClass.respondsTo(str,methodName)?'yes':'no'

//getMetaMethod() 获取元方法 MetaMethod
MetaMethod methodOfInterest=str.metaClass.getMetaMethod(methodName)
println methodOfInterest.invoke(str)

List<MetaMethod> metaMethods=str.metaClass.getMetaMethods()
metaMethods.each {print it.name+','}
println ''
println "Does String responds to ${staticMethodName}()?"
println String.metaClass.respondsTo(str,staticMethodName)?'yes':'no'

println "Does String responds to compareTo(String)?"
println String.metaClass.respondsTo(str,'compareTo',"test")?'yes':'no'
/**
 * 执行static 方法
 */
println String.metaClass.invokeStaticMethod(str,staticMethodName,"%s-world",str)
/**
 * 如果要找一个static 方法 使用 getStaticMetaMethod()
 * FIXME java.lang.IllegalArgumentException: argument type mismatch
 */
//staticMethodOfInterest=str.metaClass.getStaticMetaMethod(staticMethodName,"test","test")
//println staticMethodOfInterest.invoke(str,"%s-world",'hi')

println "Does String has property ${property}?"
println String.metaClass.hasProperty(str,property)?'yes':'no'
