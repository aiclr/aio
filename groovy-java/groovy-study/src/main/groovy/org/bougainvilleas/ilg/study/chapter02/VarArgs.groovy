package org.bougainvilleas.ilg.study.chapter02

/**
 * 支持省略符号标记形参 实现变长参数
 */
def receiveVarArgs(int a,int...b){
    println "You passed $a and $b"
}

/**
 * 以数组作为末尾形参的方法 实现变长参数
 */
def receiveArray(int a,int[] b){
    println "You passed $a and $b"
}

receiveVarArgs(1,2,3,4,5)
receiveArray(1,2,3,4,5)


/**
 * 对于接受变长参数或者以数组作为末尾形参的方法
 * 可以想起发送数组或离散的值
 * 发送数组而非离散值时,请务必谨慎
 * groovy会将包围在放括号中的值看作ArrayList的一个实例,而不是纯数组
 * 如果简单地发送如[2,3,4,5]这样的值,将出现 MethodMissingException
 * 要发送数组可以
 * 定义一个指向该数组的引用
 * 或者
 * 使用 as 操作符
 */
int[] values=[2,3,4,5]
receiveVarArgs(1,values)

receiveVarArgs(1,[2,3,4,5] as int[])
