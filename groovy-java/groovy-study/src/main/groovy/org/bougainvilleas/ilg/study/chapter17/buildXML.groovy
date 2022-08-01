package org.bougainvilleas.ilg.study.chapter17

/**
 * MarkupBuilder 适合小到中型文档
 * 文档非常大若干兆字节，则使用 StreamingMarkupBuilder 内存占用会好一些
 *
 * MarkupBuilder 并没有 languages 方法
 * MarkupBuilder 假定 调用 languages 是定义一个 根元素
 * languages 内闭包 是一个上下文
 * 领域特定语言与上下文有关
 * 在闭包内，备调用到的任何不存在的方法，都会被假定为是一个子元素的名字
 * 如果在调用方法时，传递的是Map 参数 language(name:'C++') 货呗当作元素的属性   <language name='C++'>
 * 如果是任何单个的参数值 author('Stroustrup') 标识的是元素内容。而非属性 <author>Stroustrup</author>
 *
 * MarkupBuilder 会将结果写到标准输出
 */
bldr = new groovy.xml.MarkupBuilder()
bldr.languages {
    language(name: 'C++') { author('Stroustrup') }
    language(name: 'Java') { author('Gosling') }
    language(name: 'Lisp') { author('McCarthy') }
}

System.err.println('\n结果写到 StringWriter')
/**
 * MarkupBuilder 可以附上一个 writer 将结果写到 writer
 */
langs = ['C++': 'Stroustrup', 'Java': 'Gosling', Lisp: 'McCarthy']
writer = new StringWriter()
bldr = new groovy.xml.MarkupBuilder(writer)
bldr.languages {
    langs.each { key, value ->
        languages(naem: key) {
            author(value)
        }
    }
}
System.err.println(writer)

/**
 * StreamingMarkupBuilder 借助该生成器支持的属性 mkp 声明 namespace、 xml 注释等内容
 * 一旦定义了 namespace 要将元素与 namespace 关联起来，在前缀上使用点记号即可 如： computer.language(name:key)
 */
System.err.println("大文件 用StreamingMarkupBuilder 优化内存占用")
xmlDocument = new groovy.xml.StreamingMarkupBuilder().bind {
    mkp.xmlDeclaration()
    //<languages xmlns:computer='Computer'>
    // 前缀 是 computer ，namespace 是 Computer
    mkp.declareNamespace(computer: "Computer")
    languages {
        //注释 <!--Created using StreamingMarkupBuilder-->
        comment << "Created using StreamingMarkupBuilder"

        langs.each { key, value ->
            //<computer:language name='C++'>。
            // 将元素与 namespace 关联起来 在前缀上使用点记号即可 computer.language(name:key)
            computer.language(name: key) {
                //<author>Stroustrup</author>
                author(value)
            }
        }
    }
}
println xmlDocument