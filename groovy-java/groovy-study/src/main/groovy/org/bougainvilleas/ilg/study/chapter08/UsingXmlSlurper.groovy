package org.bougainvilleas.ilg.study.chapter08

import groovy.xml.XmlSlurper

/**
 * 较大文档 XmlParser 内存使用让人难以忍受
 * XmlSlurper 执行惰性求值 所有内存使用比较友好 开销较低
 */

languages = new XmlSlurper().parse('languages.xml')
println 'Languages and authors'

languages.language.each {
    println "${it.@name} authored by ${it.author[0].text()}"
}

def languagesByAuthorName = { authorName ->
    languages.language.findAll { authorName == it.author[0].text() }
            .collect { it.@name }
            .join(', ')
}

println "Languages by Wirth:" + languagesByAuthorName('Wirth')
