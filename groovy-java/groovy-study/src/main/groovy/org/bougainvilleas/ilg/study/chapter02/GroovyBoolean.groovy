package org.bougainvilleas.ilg.study.chapter02

//非集合的引用类型 null视作false 非null 视作true
str='hello'
if(str) {println "$str"}

lst0=null
println lst0?'lst0 true':'lts0 false'

//集合引用类型 null或空集合视作false,非空集合 视作true
lst1=[1,2,3]
println lst1?'lst1 true':'lst1 false'

lst2=[]
println lst2?'lst2 true':'lst2 false'