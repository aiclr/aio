package org.bougainvilleas.ilg.designpattern

/**
 * Proxy Pattern
 * 代理模式允许一个对象充当其他对象的假装替换。
 * 一般来说，无论是谁在使用代理，都不会意识到他们没有使用真实的东西。
 * 当真实对象很难创建或使用时，该模式很有用：
 *      它可能存在于网络连接上，或者是内存中的大型对象，或者是文件、数据库或其他昂贵或不可能复制的资源。
 */
class AccumulatorProxy {
    def accumulate(args) {
        def result
        def s = new Socket("localhost", 54321)
        s.withObjectStreams { ois, oos ->
            oos << args
            result = ois.readObject()
        }
        s.close()
        return result
    }
}

println new AccumulatorProxy().accumulate([1, 2, 3, 4, 5, 6, 7, 8, 9, 10])