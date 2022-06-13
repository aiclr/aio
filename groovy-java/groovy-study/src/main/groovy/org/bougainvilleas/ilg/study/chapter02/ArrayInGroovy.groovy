package org.bougainvilleas.ilg.study.chapter02

//java
int[] arr = new int[]{1, 2, 3, 4, 5};

//groovy
int[] arr1 = [1, 2, 3, 4, 5]
println arr1
//class is [I
println 'class is ' + arr1.getClass().name

def arr2 = [1, 2, 3, 4, 5] as int[]
println arr2
//class is [I
println 'class is ' + arr2.getClass().name

// 不带 as int[] groovy 会创建 ArrayList 实例
def arr3 = [1, 2, 3, 4, 5]
println arr3
//class is java.util.ArrayList
println 'class is ' + arr3.getClass().name