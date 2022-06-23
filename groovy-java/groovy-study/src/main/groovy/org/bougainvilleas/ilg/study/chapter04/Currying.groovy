package org.bougainvilleas.ilg.study.chapter04

/**
 * 科理化闭包 减少代码中的噪音
 * 科理化位于前面开始的连续若干个参数 curry() {@link groovy.lang.Closure#curry(final Object... arguments)}
 * 科理化位于中间的形参使用 ncurry() {@link groovy.lang.Closure#ncurry(int n, final Object... arguments)}
 *      提供形参位置 和 形参对应的值
 * 科理化后面的形参 rcurry() {@link groovy.lang.Closure#rcurry(final Object... arguments)}
 *
 * f(x,y)->z
 *
 * curry(f):x->(y->z)
 *
 * 科里化 有助于简化数学证明方法
 */

def tellFortunes(closure){
    Date date = new Date("06/22/2022")
    closure date ,"Your day is filled with ceremony"
    closure date ,"They're features, not bugs"

    //通过 科里化 避免重复发送date
    //postFortune 保存着科里化之后的闭包的引用，它已经预先绑定了date 的值
    //传入第二个形参,科理化闭包负责把 预先绑定的 date 和 fortune 发送给原来的闭包
    postFortune = closure.curry(date)
    //相当于 closure.curry(date) "Your day is filled with ceremony"
    postFortune "Your day is filled with ceremony"
    //相当于 closure.curry(date) "They're features, not bugs"
    postFortune "They're features, not bugs"
}



tellFortunes(){date,fortune->
    println "Fortune for ${date} is '${fortune}'"
}