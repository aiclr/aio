package org.bougainvilleas.ilg.study.chapter02

class GroovyWizard {
    def static learn(trick,action)
    {
        //...
        println "$trick"
        this//相当于 return this
    }
}

/**
 * 静态方法内可以使用this来引用 Class对象
 * learn() 返回Class对象,所以可以使用链式调用
 */
GroovyWizard.learn('alohomora',{/***/})
        .learn('expelliarmus',{/***/})
        .learn('lumos',{/***/})
