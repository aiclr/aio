package org.bougainvilleas.ilg.study.chapter04

/**
 * 判偶 不判奇
 * 使用 & 与运算 判断奇偶
 */
def totalSelectValues(n, closure) {
    total = 0
    for (i in 1..n) {
        if (closure(i)) {
            total += i
        }
    }
    total
}

print "Total of even numbers from 1 to 10 is "
println totalSelectValues(10) { 0 == (it & 1) }

def isOdd = { (it & 1) == 1 }
print "Total of odd numbers from 1 to 10 is "
println totalSelectValues(10, isOdd)

println "-1%2=${-1 % 2}"
println "-1&1=${-1 & 1}"


class Equipment {
    def calculator

    Equipment(calc) { calculator = calc }

    def simulate() {
        println "Running simulation"
        calculator()
    }
}

def eq1 = new Equipment({ println "Calculator 1" })
def aCalculator = { println "Calculator 2" }
def eq2 = new Equipment(aCalculator)
def eq3 = new Equipment(aCalculator)
eq1.simulate()
eq2.simulate()
eq3.simulate()

/**
 * 向闭包传递参数
 */
def tellFortune(closure) {
    closure new Date("06/21/2022"), "Your day is filled with ceremony"
}

tellFortune() {
    date, fortune ->
        println "Fortune for ${date} is '${fortune}'"
}

/**
 * 使用闭包清理资源
 * 当从闭包返回时,withWriter()会自动刷新 flush 并关闭这个流
 * 不需要手动调用 writer.close() 关闭流
 */
new FileWriter('/tmp/output.txt')
        .withWriter { writer ->
            writer.write('a')
        }

/**
 * 与AST 自动创建 use() 对比 {@link org.bougainvilleas.ilg.study.chapter16.eam.EAMTransformation}
 *
 *  Execute Around Method 模式
 *  自定义闭包清理资源
 *  如果有一对必须连续执行的动作
 *  比如打开-关闭,可以使用 Execute Around Method 模式(一种 Smalltalk 模式)
 *  编写一个 Execute Around 方法 ,接收一个块作为参数
 *  在这个方法中,把对该块的调用夹到 打开-关闭方法的调用之间
 *
 */
class Resource{
    def open(){ print "opened..."}
    def close(){ print "close..."}
    def read(){ print "read..."}
    def write(){ print "write..."}

    def static use(closure){
        def r= new Resource()
        try{
            r.open()
            closure(r)
        }finally{
            r.close()
        }
    }
}
def resource=new Resource()
resource.open()
resource.read()
resource.write()
/**
 * 自定义闭包清理资源
 */
Resource.use{
    res->
        res.read()
        res.write()
}
