package org.bougainvilleas.ilg.study.chapter15
/**
 * Expando 在构建时，可以使用一个map 为其指定属性和方法
 * 也可以动态地随时指定
 */
carA=new Expando()
//可以使用一个map 为其指定属性和方法
carB=new Expando(year:2021,miles:0)
carC=new Expando(year:2021,miles:0,turn:{println 'turning ...'})
// 也可以动态地随时指定
carA.year=2022
carA.miles=10
carB.drive={
    miles+=10
    println "$miles miles driven"
}

println "carA: "+carA
println "carB: "+carB

carB.drive()
carC.turn()