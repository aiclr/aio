package org.bougainvilleas.ilg.study.chapter08

langs = ['C++': 'Stroustrup', 'Java': 'Gosling', 'Lisp': 'McCarthy']

xmlDocument=new groovy.xml.StreamingMarkupBuilder().bind{
    mkp.xmlDeclaration()
    mkp.declareNamespace(computer:"Computer")
    languages{
        comment << "Created using StreamingMarkupBuilder"
        langs.each{key,value->
            computer.language(name:key){
                author(value)
            }
        }
    }
}
println xmlDocument