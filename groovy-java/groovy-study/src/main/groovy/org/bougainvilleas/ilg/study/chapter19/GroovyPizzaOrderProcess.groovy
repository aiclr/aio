package org.bougainvilleas.ilg.study.chapter19

/**
 * 将两个脚本放到一起执行
 */
def dslDef=new File('GroovyPizzaDSL.groovy').text
def dsl=new File('orderPizza.dsl').text

def script="""
${dslDef}
acceptOrder {
${dsl}
}
"""
new GroovyShell().evaluate(script)