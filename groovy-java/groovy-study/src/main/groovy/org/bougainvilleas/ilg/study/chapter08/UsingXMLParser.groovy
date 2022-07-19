package org.bougainvilleas.ilg.study.chapter08

import groovy.xml.XmlParser

/**
 * groovy.util.XmlParser 利用Groovy的动态类型和元编程能力
 * 可以直接使用名字访问文档中的成员
 *
 * 无use()块
 * 注意
 * XmlParser没有保留XML InfoSet 忽略了文档中的XML注释和处理指令
 * 较大文档 内存使用高
 */

languages = new XmlParser().parse('languages.xml')
println 'Languages and authors'

languages.each {
    println "${it.@name} authored by ${it.author[0].text()}"
}

def languagesByAuthor = { authorName ->
    languages.findAll { authorName == it.author[0].text() }
            .collect { it.@name }
            .join(', ')
}
println "Languages by Wirth:" + languagesByAuthor('Wirth')