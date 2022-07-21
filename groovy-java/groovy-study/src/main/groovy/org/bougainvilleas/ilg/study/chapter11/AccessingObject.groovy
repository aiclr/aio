package org.bougainvilleas.ilg.study.chapter11

/**
 * 动态访问对象
 */
def printInfo(obj){
    usrRequestedProperty='bytes'
    usrRequestedMethod='toUpperCase'
    println obj[usrRequestedProperty]
    println obj."$usrRequestedProperty"
    println obj."$usrRequestedMethod"()
    println obj.invokeMethod(usrRequestedMethod,null)
}
printInfo('hello')


println "properties of 'hello' are: "
'hello'.properties.each {println it}