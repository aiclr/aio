package org.bougainvilleas.ilg.study.chapter12

class Bus{
    def check() { System.err.println("check called...") }

    def start() { System.err.println("start called...") }

    def drive() { System.err.println("drive called...") }
}

/**
 * 无权限修改类的源代码
 * 使用 MetaClass 实现 invokeMethod() 方法 并以此拦截方法
 *
 * Bus 未实现 GroovyInterceptable  无 invokeMethod
 * 下面以闭包形式实现 invokeMethod
 * 此外使用 delegate 代替this
 * 在用于拦截到闭包内 delegate 指向要拦截其方法的目标对象 bus
 */
Bus.metaClass.invokeMethod = { String name, args ->
    System.err.println("Call to $name intercepted...")
    if (name != 'check') {
        System.err.println("running filter...")
        /**
         * 此处使用 delegate
         */
        Bus.metaClass.getMetaMethod('check').invoke(delegate, null)
    }
    def validMethod = Bus.metaClass.getMetaMethod(name, args)
    if (validMethod != null) {
        /**
         * 此处使用 delegate
         */
        validMethod.invoke(delegate, args)
    } else {
        /**
         * 此处使用 delegate 且不是调用 invokeMethod 而是调用 invokeMissingMethod
         * 此闭包已经在 invokeMethod方法中，再调用就递归了
         */
        Bus.metaClass.invokeMissingMethod(delegate, name, args)
    }
}


bus=new Bus()
bus.start()
bus.drive()
bus.check()
try{
    bus.speed()
}catch(Exception e){
    System.err.println(e)
}