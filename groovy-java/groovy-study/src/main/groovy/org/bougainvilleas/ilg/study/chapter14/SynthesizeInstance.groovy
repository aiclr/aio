package org.bougainvilleas.ilg.study.chapter14
/**
 * 在实力层次合成方法，在测试中，或者在一个web应用的特定web请求中，
 * 可以修改一个选定的实例的行为，而不影响java虚拟机中该实例所关联的类
 *
 * 能够基于实例的当前状态或实例所接受的输入创建动态的方法或行为
 *
 */
class Person5{}

def emc=new ExpandoMetaClass(Person5)
emc.methodMissing={String name,args->
    "I'm Jack of all trades... I can $name"
}
emc.initialize()

def jack=new Person5()
def paul=new Person5()
jack.metaClass=emc
println jack.sing()
println jack.dance()
println jack.juggle()
try{
    println paul.sing()
}catch(ex)
{
    println ex
}

jack.metaClass=null
try{
    println jack.sing()
}catch(ex)
{
    println ex
}