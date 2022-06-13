package org.bougainvilleas.ilg.study.chapter02

/**
 * groovy 不可省略的分号 情况
 * 如果要使用的是一个静态初始化器
 * 而不是实例化初始化器,就没有这个问题
 *
 * 如果有理由同时使用这两种初始化器
 * 可以将静态初始化器放在实例初始化器之前,从而避免使用分号
 */
class Semi {

//    def val = 3

    def val = 3;

    /**
     * 下面 本意是 类的实例初始化器
     * 由于上面  def val = 3
     * 无分号,把实例化初始化器看成 def val = 3 属性定义的一部分
     * 报错 groovy.lang.MissingMethodException
     * 把 def val = 3加分号即可正常运行
     */
    {
        println ' Semi Instance Initializer called ...'
    }
}

println new Semi()

//掉转顺序
class Semi1 {
    {
        println ' Semi1 Instance Initializer called ...'
    }
    def val = 3
}

println new Semi1()


//使用静态初始化器
class Semi2 {
    def val = 3
    static {
        println ' Semi2 static Instance Initializer called ...'
    }
}

println new Semi2()

//将静态初始化器放在实例初始化器之前,从而避免使用分号
class Semi3 {
    static {
        println ' Semi3 static Instance Initializer called ...'
    }
    {
        println '  Semi3 Instance Initializer called ...'
    }
}

println new Semi3()


