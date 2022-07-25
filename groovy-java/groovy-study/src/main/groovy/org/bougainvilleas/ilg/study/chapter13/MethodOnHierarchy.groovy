package org.bougainvilleas.ilg.study.chapter13

/**
 * 声明一个闭包变量
 * 可以多次使用
 */
daysFromNow = { ->
    Calendar today = Calendar.instance
    today.add(Calendar.DAY_OF_MONTH, (int) delegate)
    today.time
}

Integer.metaClass.daysFromNow = daysFromNow
Long.metaClass.daysFromNow = daysFromNow;

println 5.daysFromNow()
println 5L.daysFromNow()

/**
 * 在　Number 基类上提供 方法
 * 在其派生类上也可以调用注入方法
 */
Number.metaClass.someMethod = { ->
    println "Number someMethod called"
}

5.someMethod()
5L.someMethod()
(5 as Byte).someMethod()
(5 as Short).someMethod()
(5 as Float).someMethod()
(5 as double).someMethod()


/**
 * 注入静态方法
 * & 运算 判断奇数偶数
 * true 偶数
 * false 奇数
 */
Integer.metaClass.'static'.isEven = { val -> (val & 1) == 0 }

println "is 2 even? " + Integer.isEven(2)
println "is -1 even? " + Integer.isEven(-1)


/**
 * 通过定义一个名为 constructor 的特殊属性可以加入构造器
 * 因为是添加一个构造器，不是替换一个现有构造器，所以使用了<< 操作符
 * 注意： 使用 << 操作符 来覆盖现有的构造器或方法，groovy会报错
 * 下面例子是新增一个参数为 Calendar 的构造器
 * 获取当前的日期
 */
Integer.metaClass.constructor << { Calendar calendar ->
    //此处使用了Integer中现有的接受一个int的构造器，务必确认没有递归调用，否则会 stackoverflowError
    println 'Intercepting constructor call Calendar'
    new Integer(calendar.get(Calendar.DAY_OF_MONTH))
    //也可以直接返回 calendar.get(Calendar.DAY_OF_MONTH)
//    calendar.get(Calendar.DAY_OF_MONTH)
}

println new Integer(Calendar.instance)


/**
 * 使用 = 覆盖现有构造器，严格来讲 构造器是不可覆盖的
 * 在覆盖的 构造器内仍然可以使用反射调用原来的实现
 */
Integer.metaClass.constructor={int val->
    println 'Intercepting constructor call'
    //反射创建 Integer
    constructor= Integer.class.getConstructor(Integer.TYPE)
    constructor.newInstance(val)
}
println new Integer(4)
//new Integer(Calendar) 调用了新注入的 new Integer(int)
println new Integer(Calendar.instance)