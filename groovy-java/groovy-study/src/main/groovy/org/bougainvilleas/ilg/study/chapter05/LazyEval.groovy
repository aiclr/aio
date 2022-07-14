package org.bougainvilleas.ilg.study.chapter05

/**
 * groovy 惰性求值 lazy evaluation
 * 把一个表达式保存在一个字符串中 稍后再打印
 *
 * 工作原理
 * 当对一个GString 实例求值时
 * 如果其中包含一个变量，该变量的值会被简单地打印到一个Writer，通常是一个 StringWriter
 * 如果 GString 包含的是一个闭包 而非变量 该闭包就会被调用
 *      如果闭包接收一个参数 GString 会把 Writer 对象当作一个参数发送给他
 *      如果闭包不接受任何参数 GString会简单地调用该闭包 并打印我们想返回给 Writer 的结果
 *      如果闭包接收的参数不止一个 调用会失败 并抛出异常
 *
 *  如果希望改变表达式中使用的引用
 *  希望他们的当前值被用于惰性求值中
 *  不要在表达式中直接替换他们
 *  要使用一个无参闭包
 */
what = new StringBuilder('fence')
text = "The cowjumped over the $what"
println text
what.replace(0,5,"moon")
println text
/**
 * text 是 GString 实例
 * text 这个 GString 实例中包含了变量 what
 * 该表达式会在每次被打印时，在其上调用 toString() 方法时求值
 * 修改 what 所指向的 StringBuilder 实例的值，在打印时会有所体现
 */
println text.getClass().name



price = 684.71
company = 'google'

quote = "Today $company stock closed at $price"
println quote
println quote.getClass().name

stocks=[Apple:663.01,Microsoft:30.95]
println '无效GString'
stocks.each{key,value->
    company = key
    price=value
    println quote
}
println '解决方案'
priceClosure={it.write("$price")}
companyClosure={it.write(company)}
quote = "Today $companyClosure stock closed at $priceClosure"
stocks.each{key,value->
    company = key
    price=value
    println quote
}
println '解决方案'
priceClosure={->price}
companyClosure={->company}
quote = "Today $companyClosure stock closed at $priceClosure"
stocks.each{key,value->
    company = key
    price=value
    println quote
}

println '解决方案'
quote = "Today ${->company} stock closed at ${->price}"
stocks.each{key,value->
    company = key
    price=value
    println quote
}