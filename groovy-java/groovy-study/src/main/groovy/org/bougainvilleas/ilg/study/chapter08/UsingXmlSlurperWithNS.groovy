package org.bougainvilleas.ilg.study.chapter08

import groovy.xml.XmlSlurper

/**
 * 命名空间 namespace 不是url 但是需要保持唯一性
 * xml文档中 namespace 前缀不是独一无二的可以随便起（要有一些规范和约束）
 * 使用 declareNamespace(human:'Natural',comp:'Computer') 关联
 * 参数map 键为前缀 值为namespace的值
 * 定义前缀后，GPath 查询也就可以获得名字的前缀
 *
 * languages.language 返回所有子元素
 * languages.'human:language' 返回命名空间为 natural 的所有子元素
 * languages.'comp:language' 返回命名空间为 computer 的所有子元素
 */
languages = new XmlSlurper().parse('namespacexml.xml').declareNamespace(human:'Natural',comp:'Computer')

print "Languages: "
println languages.language.collect{it.@name}.join(', ')

print "Natural languages: "
println languages.'human:language'.collect{it.@name}.join(', ')

print "Computer languages: "
println languages.'comp:language'.collect{it.@name}.join(', ')