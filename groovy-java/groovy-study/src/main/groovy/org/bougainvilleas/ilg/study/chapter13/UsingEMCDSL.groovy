package org.bougainvilleas.ilg.study.chapter13

/**
 * ExpandoMetaClass(EMC) DSL 添加大量方法
 *
 * 将每个实例方法的代码包在一个闭包中，然后将其赋值给想注入的方法名
 *
 * 注入静态方法 ，定义一个以单词 static 为前缀的闭包，并将静态方法的定义放在这个闭包内
 *
 * 构造器 使用 constructor
 *
 * EMC DSL 减少代码噪音，将扩展方法 集中到一起，更容易维护
 *
 * 限制:
 * ExpandoMetaClass 注入的方法 只能从 Groovy 代码内调用
 * 不能从编译过的java代码中调用
 * 不能从java代码中通过反射来使用
 *
 * 可以使用变通方案从java调用 参考
 * {@link org.bougainvilleas.ilg.study.chapter10.CallDynamicMethod}
 *
 */
Integer.metaClass {
    daysFromNow = { ->
        Calendar today = Calendar.instance
        today.add(Calendar.DAY_OF_MONTH, delegate as int)
        today.time
    }

    getDaysFromNow = { ->
        Calendar today = Calendar.instance
        today.add(Calendar.DAY_OF_MONTH, delegate as int)
        today.time
    }
    'static' {
        isEven = { val -> (val & 1) == 0 }
    }
    constructor = { Calendar calendar ->
        //此处使用了Integer中现有的接受一个int的构造器，务必确认没有递归调用，否则会 stackoverflowError
        println 'Intercepting constructor call Calendar'
        new Integer(calendar.get(Calendar.DAY_OF_MONTH))
    }
    constructor={
        int val->
            println 'Intercepting constructor call'
            //反射创建 Integer
            constructor= Integer.class.getConstructor(Integer.TYPE)
            constructor.newInstance(val)
    }
}

/**
 * 当某个不存在的方法被调用时，该方法回介入
 */
def methodMissing(String name,args){
    println "You called $name with ${args.join(', ')}."
    args.size()
}


println 5.daysFromNow()
println 5.getDaysFromNow()

println "is 2 even? " + Integer.isEven(2)
println "is -1 even? " + Integer.isEven(-1)

println new Integer(4)
//new Integer(Calendar) 调用了新注入的 new Integer(int)
println new Integer(Calendar.instance)

