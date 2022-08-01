package org.bougainvilleas.ilg.study.chapter17

import groovy.json.JsonSlurper

/**
 * JsonBuilder 可以从 JavaBean HashMap 和列表生成 JSON格式的输出
 * JSON格式的输出被保存到内存中，可以稍后将其写入到一个流中或是用于进一步处理
 *
 * 如果不想将数据保存在内存中，而想在创建是直接将其变为流，使用 StreamingJsonBuilder
 *
 * 反向代理 利用groovy 提供的 JsonSlurper 从JSON 数据创建 HashMap
 * 可以使用 parseText() 方法读取 包含在String 中的JSON 数据
 * 可以使用 parse() 方法从Reader 或文件中读取 JSON 数据
 */
class Person{
    String first
    String last
    def sigs
    def tools
}
println("POGO 转 JSON")
john=new Person(first: 'John',last: 'Smith',sigs: ['Java','Groovy'],tools: ['script':'Groovy','test':'Spock'])
bldr=new groovy.json.JsonBuilder(john)
writer=new StringWriter()
bldr.writeTo(writer)
println writer

println("获取 POGO 组装 JSON")
bldr=new groovy.json.JsonBuilder()
bldr{
    firstName john.first
    lastName john.last
    "special interest groups" john.sigs
    "preferred tools"{
        numberOfTools john.tools.size()
        tools john.tools
    }
}
writer=new StringWriter()
bldr.writeTo(writer)
println writer


println("从文件读取json 转换为 POGO")
def slurper = new JsonSlurper()
def person=slurper.parse(new FileReader('Person.json'))
println "$person.first $person.last is interested in ${person.sigs.join(", ")}"