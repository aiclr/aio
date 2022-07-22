package org.bougainvilleas.ilg.study.chapter12

/**
 * 在 Integer 的 MetaClass 上添加 invokeMethod()
 * 拦截不存在的方法的调用，应该使用 methodMissing() 来代替 invokeMethod()
 * 可以在 MetaClass 上同时提供 invokeMethod() methodMissing()
 * invokeMethod() 会优先于 methodMissing() 处理
 * 但是 invokeMissingMethod() 作用就是尝试调用 methodMissing 失败则抛异常
 */
Integer.metaClass.invokeMethod{
    String name,args->
        System.err.println("Call to $name intercepted on $delegate...")
        def validMethod=Integer.metaClass.getMetaMethod(name,args)
        if(validMethod==null)
        {
            Integer.metaClass.invokeMissingMethod(delegate,name,args)
        }else
        {
            System.err.println("running pre-filter...")
            //如果实现环绕建议，则会去掉此语句
            result = validMethod.invoke(delegate,args)
            System.err.println("running post-filter...")
            result
        }
}
println 5.floatValue()
println 5.intValue()
try{
    println 5.empty()
}catch(Exception e)
{
    System.err.println(e)
}
