package org.bougainvilleas.ilg.study.chapter10

println 'In Script2'
shell=new GroovyShell()
shell.evaluate(new File('Script1.groovy'))
//更简单的调用方式
evaluate(new File('Script1.groovy'))