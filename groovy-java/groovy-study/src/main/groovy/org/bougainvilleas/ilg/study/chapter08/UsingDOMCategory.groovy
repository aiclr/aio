package org.bougainvilleas.ilg.study.chapter08

/**
 * groovy 的分类 在类上定义动态方法
 * 其中一个分类可用于处理文档对象模型 (Document Object Model，DOM) --- DOMCategory
 * DOMCategory 可以通过类GPath(Groovy path expression 即groovy 路径表达式)的符号在DOM结构中导航
 *
 * 通过子元素的名字访问所有子元素   languages = rootElement.language
 * DOMBuilder 的 parse() 方法把文档加载到内存中
 * 在属性名前放一个 @ 可以获得该属性的值 language.@name
 *
 *
 *
 * GPath
 * 于XPath可以帮助导航XML文档的层次结构类似
 * GPath可以帮助导航对象（Plain Old Java Object和Plain Old Groovy Object 即 POJO 和 POGO）
 * GPath可以邦族导航XML 的层次结构
 * 可以使用句号符号遍历层次结构
 * 如 language.author
 *      对象：通过 Language实例的getAuthor() 访问其Author属性
 *      xml：获取language元素的一个子元素 author
 *  注意：
 *         language.@name @符号说明要访问的时属性而非子元素
 *
 *
 */
document = groovy.xml.DOMBuilder.parse(new FileReader("languages.xml"))

rootElement = document.documentElement
/**
 * 要使用DOMCategory 必须把代码放到 use() 块内
 */
use(groovy.xml.dom.DOMCategory) {
    println "Languages and authors"
    languages = rootElement.language
    languages.each { language ->
        println "${language.'@name'} authored by ${language.author[0].text()}"
    }

    def languagesByAuthor = { authorName ->
        languages.findAll { it.author[0].text() == authorName }
                .collect { it.'@name' }
                .join(', ')
    }
    println "Language by Wirth:" + languagesByAuthor('Wirth')
}
