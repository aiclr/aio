package org.bougainvilleas.ilg.study.chapter07


/**
 * JavaScript 和 VBScript 都有 with，支持创建上下文 context
 * 再with 作用域内调用任何方法 都会被定向到该上下文对象，去掉对该实例的多余引用
 * groovy中 Object with() 方法提供了同样功能 with方法是作为 identity的同义词引入的 可以互换使用
 * 该方法接收一个闭包，作为参数
 * 在闭包内调用任何方法都会被自动解析到上下文对象
 */
println '未利用with()'
lst=[1,2]
lst.add(3)
lst.add(4)
println lst.size()
println lst.contains(2)

/**
 * with 方法利用 闭包的delegate属性
 * 调用 with 方法时，会将闭包的delegate 属性设置到调用 with的对象上
 * delegate 会处理 this 不处理的方法
 *
 * with方法使我们更方便地在一个对象上调用多个方法，利用上下文 context 减少混乱
 * 在 构建领域特定语言 DSL 非常有用
 * 可以实现类似脚本的调用，隐式地将其路由到幕后的对象
 */
println '利用with()'
lst=[1,2]
lst.with {
    add(3)
    add(4)
    println size()
    println contains(2)
    println "this is ${this}"
    println "owner is ${owner}"
    println "delegate is ${delegate}"
}
