package org.bougainvilleas.ilg.study.chapter02

def foo(str) {
    str?.reverse()
}

println foo('evil')
//不会抛出 NPE
println foo(null)