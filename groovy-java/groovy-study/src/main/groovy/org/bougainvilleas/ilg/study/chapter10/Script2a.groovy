package org.bougainvilleas.ilg.study.chapter10

println 'In Script2a'
name="Venkat"//定义变量name 注意下面的脚本内也定义有同名 name
/**
 * 创建 GroovyShell实例时，将当前脚本的Binding 对象传递给它（每个脚本执行时都有一个Binding对象）
 * 被调用脚本现在就可以使用（读取和设置）发起调用脚本所知道的变量
 *
 * 如果不希望影响当前binding 想将其与被调用脚本的binding分开，
 * 只需要创建一个新的Binding实例 在该实例上调用setProperty()设置变量名和值，将其作为创建GroovyShell实例时的一个参数
 *
 * run()
 *
 */
shell=new GroovyShell(binding)
//返回值 groovy 最后一行 会作为返回值
result=shell.evaluate(new File('Script1a.groovy'))
println "Script1a returned: $result"
//直接使用脚本中变量
println "Hello $name"

/**
 * 不希望影响当前binding 想将其与被调用脚本的binding分开，
 */
binding1=new Binding()
binding1.setProperty('name','VenkatVenkat')
shell=new GroovyShell(binding1)
shell.evaluate(new File('Script1a.groovy'))

println "Hello $name"

binding2=new Binding()
binding2.setProperty('name','DanDan')
shell=new GroovyShell(binding2)
shell.evaluate(new File('Script1a.groovy'))

println "Hello $name"