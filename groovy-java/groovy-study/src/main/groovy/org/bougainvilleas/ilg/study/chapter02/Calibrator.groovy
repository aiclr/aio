package org.bougainvilleas.ilg.study.chapter02

/**
 * groovy 闭包使用花括号 {} 定义
 * 匿名内部类也是 花括号 {}
 *
 * 与传递内联的闭包相比,传递引用包噪音会小一些
 */

class CalibratorSub{
    //构造器接收一个闭包(函数/代码块)作为参数
    CalibratorSub(calculationBlock){
        print 'using ...'
        calculationBlock()
    }
}
/**
 * 正常情况 可以通过把一个代码块 附到函数调用末尾,将闭包传递给函数
 * instance.method() {闭包}
 *
 * 如下向 接受闭包的构造器传递 一个闭包来实例化一个实例
 * groovy会认为要创建一个匿名内部类 会报告错误
 */
//def calibrator = new CalibratorSub() {
//    println 'the calculation provided'
//}


/**
 * 修改调用方式
 * 将闭包放到构造器调用语句的圆括号内
 */
def calibrator = new CalibratorSub({
    println 'the calculation provided'
})

//定义闭包
def calculation = {println 'another calculation provided'}
//传递闭包
def calibrator2 = new CalibratorSub(calculation)
