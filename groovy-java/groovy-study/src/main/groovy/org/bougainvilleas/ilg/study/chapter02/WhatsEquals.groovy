package org.bougainvilleas.ilg.study.chapter02

/**
 * 当实现 Comparable 接口时 会将 ==  映射到 compareTo() 方法
 */
class A {
    boolean equals(other) {
        println "equals called"
        false
    }
}

class B implements Comparable {
    boolean euqals(other) {
        println "equals called"
        false
    }

    int compareTo(other) {
        println "compareTo called"
        0
    }
}

new A() == new A()

new B() == new B()