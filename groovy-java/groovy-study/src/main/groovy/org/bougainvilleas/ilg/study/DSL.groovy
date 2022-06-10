package org.bougainvilleas.ilg.study

import groovy.xml.MarkupBuilder

class Section {
    String title
    String body
}

class MemoDSL {
    String toText
    String fromText
    String body
    def sections=[]
    def static make(closure){
        MemoDSL memoDSL=new MemoDSL()
        closure.delegate=memoDSL
        closure()
    }

    def to(String toText) {
        this.toText=toText
    }
    def from(String fromText){
        this.fromText=fromText
    }
    def body(String body){
        this.body=body
    }

    def methodMissing(String methodName,args){
        def section=new Section(title: methodName,body: args[0])
        sections << section
    }

    static doXml(MemoDSL memoDSL){
        def writer = new StringWriter()
        def xml = new MarkupBuilder(writer)
        xml.memo() {
            to(memoDSL.toText)
            from(memoDSL.fromText)
            body(memoDSL.body)
            for(s in memoDSL.sections){
                "$s.title"(s.body)
            }
        }
        println writer
    }

    static doHtml(MemoDSL memoDSL){
        def writer = new StringWriter()
        def html=new MarkupBuilder(writer)
        html.html(){
            head{
                title('Memo')
            }
            body{
                h1('Memo')
                h2("To: ${memoDSL.toText}")
                h2("From: ${memoDSL.fromText}")
                p(memoDSL.body)
                for(s in memoDSL.sections){
                    p{
                        b(s.title.toUpperCase())
                    }
                    p(s.body)
                }
            }
        }
        println writer
    }

    static doText(MemoDSL memoDSL){
        String template="Memo\nTo:\t${memoDSL.toText}\nFrom: ${memoDSL.fromText}\n${memoDSL.body}\n"
        def sectionString = ''
        for(s in memoDSL.sections){
            sectionString+=s.title.toUpperCase()+':\t'+s.body+'\n'
        }
        template+=sectionString
        println template
    }


    def getXml(){
        doXml(this)
    }
    def getHtml(){
        doHtml(this)
    }
    def getText(){
        doText(this)
    }
}


MemoDSL.make{
    to'Text'
    from 'Groovy'
    body 'groovy to Text'
    extend 'other message'
    text
}


MemoDSL.make{
    to'html'
    from 'Groovy'
    body 'groovy to html'
    extend 'other message'
    html
}

MemoDSL.make{
    to'xml'
    from 'Groovy'
    body 'groovy to xml'
    extend 'other message'
    xml
}