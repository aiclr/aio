package org.bougainvilleas.ilg.study.chapter12
/**
 * 使用 GroovyInterceptable 拦截方法
 *
 * 实现了 GroovyInterceptable接口
 * 所有方法调用都会被其invokeMethod() 方法拦截
 *
 */
class Car implements GroovyInterceptable {
    def check() { System.err.println("check called...") }

    def start() { System.err.println("start called...") }

    def drive() { System.err.println("drive called...") }

    def invokeMethod(String name,args){
        System.err.println("Call to $name intercepted...")
        if(name!='check'){
            System.err.println("running filter...")
            Car.metaClass.getMetaMethod('check').invoke(this,null)
        }
        def validMethod = Car.metaClass.getMetaMethod(name,args)
        if(validMethod!=null){
            validMethod.invoke(this,args)
        }else{
            Car.metaClass.invokeMethod(this,name,args)
        }
    }
}

car=new Car()
car.start()
car.drive()
car.check()
try{
    car.speed()
}catch(Exception e){
    System.err.println(e)
}