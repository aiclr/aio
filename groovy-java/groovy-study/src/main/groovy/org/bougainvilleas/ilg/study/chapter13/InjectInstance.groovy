package org.bougainvilleas.ilg.study.chapter13

/**
 * 向实例中注入方法
 */
class Person{
    def play(){println "Playing ..."}
}
/**
 * 创建 ExpandoMetaClass
 */
def emc=new ExpandoMetaClass(Person)
/**
 * 加入方法
 */
emc.sing={->
    'oh baby baby ...'
}
/**
 * 初始化
 */
emc.initialize()


def jack=new Person()
def paul=new Person()

/**
 * 注入
 */
println '注入'
jack.metaClass=emc
println jack.sing()
try{
    paul.sing()
}catch(ex)
{
    println ex
}

/**
 * 取消注入
 */
println '取消注入'
jack.metaClass=null
try{
    println jack.sing()
    println paul.sing()
}catch(ex)
{
    println ex
}

println '简化写法'
/**
 * 简化写法
 */
jack.metaClass.sing = {->
    'oh baby baby ...'
}
println jack.sing()

jack.metaClass {
    sing={->
        'oh baby baby ...'
    }
    dance={->
        'start the music ...'
    }
}
println jack.sing()
println jack.dance()

println '取消注入'
jack.metaClass=null
try{
    println jack.sing()
    println paul.sing()
}catch(ex)
{
    println ex
}