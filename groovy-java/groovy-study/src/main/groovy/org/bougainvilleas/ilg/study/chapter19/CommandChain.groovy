package org.bougainvilleas.ilg.study.chapter19
/**
 * groovy 命令链接特性 用于创建相当流畅的简单 DSL 非常容易
 */
def (forward, left, then, fast, right) =
['forward', 'left', '', 'fast', 'right']

def move(dir) {
    println "moving $dir"
    this
}

def and(then) { this }

def turn(dir) {
    println "turning $dir"
    this
}

def jump(speed, dir) {
    println "jumping $speed and $dir"
    this
}

move forward and then turn left
jump fast, forward and then turn right

System.err.println("解析")

move(forward).and(then).turn(left)
jump(fast,forward).and(then).turn(right)