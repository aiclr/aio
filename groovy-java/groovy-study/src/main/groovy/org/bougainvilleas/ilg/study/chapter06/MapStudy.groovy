package org.bougainvilleas.ilg.study.chapter06

langs = ['C++':'Stroustrup','Java':'Gosling','Lisp':'McCarthy']
//如果键是正常的，不会造成影响 可以省略键的引号
langs = ['C++':'Stroustrup',Java:'Gosling',Lisp:'McCarthy']
println langs.getClass().name

println '使用[] 操作符访问一个键的值'
println langs['java']
println langs['C++']

/**
 * 不能在 Map实例上调用 class 属性 只能使用 getClass() 方法
 * Map 实例会假定 class 这个名字指向的是一个 键 为 class（实际不存在），返回null 再调用 name 属性就 NPE
 */
println '将 键 作为 Map的一个属性使用'
println langs.Java

/**
 * 问题原因 是Groovy 操作符重载的冲突
 * groovy 把前面的请求看作要获取 键C的值，而这个键不存在 返回null
 * 然后尝试调用 ++ 操作符所映射的 next()方法 故NPE
 * 解决方案 将麻烦的字符的键看作 String
 */
//println langs.C++ 不合法代码
println langs.'C++'

println'each迭代'
langs.each {entry->
    println "Language $entry.key was authored by $entry.value"
}
langs.each {
    println "Language $it.key was authored by $it.value"
}
langs.each {language,author->
    println "Language $language was authored by $author"
}


println'collect'
println langs.collect {language,author->
   language.replaceAll("[+]","P")
}
println langs.collect {
    it.key.replaceAll("[+]","P")
}
println langs.collect { entry->
    entry.key.replaceAll("[+]","P")
}


println'find'
println "Looking for the first language with name greater than 3 characters"
entry=langs.find{
    it.key.size()>3
}
println "Found $entry.key written by $entry.value"


println'findAll'
println "Looking for the all language with name greater than 3 characters"
selected=langs.findAll{
    it.key.size()>3
}
selected.each {
    println "Found $it.key written by $it.value"
}

/**
 * 确定某些语言的名字中包含着非字母的字符
 */
println'any 确定集合中是否有任何元素满足某些条件'
print 'Does any language name have a monoalphabetic character? '
println langs.any {
    it.key =~ /[^A-Za-z]/
}

/**
 * 确定集合中是否所有元素满足给定条件
 */
println'every 确定集合中是否所有元素满足给定条件'
print 'Does all language name have a monoalphabetic character? '
println langs.every {
    it.key =~ /[^A-Za-z]/
}


/**
 * 根据朋友的 firstName 分组
 * groupBy 闭包 指明要进行的分组
 */
println'groupBy 对元素分组'
friends=[
        briang:'Brian Goetz',
        brians:'Brian Sletten',
        davidb:'David Bock',
        davidg:'David Geary',
        scottd:'Scott Davis',
        scottl:'Scott Leberknight',
        stuarth:'Stuart Hallway'
]
groupByFirstName=friends.groupBy {it.value.split(' ')[0]}
groupByFirstName.each {firstName,buddies->
    println "$firstName : ${buddies.collect {key,fullName->fullName}.join(', ')}"
}