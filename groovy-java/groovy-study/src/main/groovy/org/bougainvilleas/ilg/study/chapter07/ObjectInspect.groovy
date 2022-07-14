package org.bougainvilleas.ilg.study.chapter07

/**
 * inspect() 获取创建一个实例需要提供什么
 *    如果类没有实现该方法 会简单返回toString()
 *    如果对象实例要接收大量输入，该方法可以帮助类的使用者再运行时确定他们应该提供的内容
 */
println 'inspect() 获取创建一个实例需要提供什么'
str='hello'
println str
println str.inspect()
