package org.bougainvilleas.ilg.study.chapter17

/**
 * 利用元编程
 * 允许调用不存在的方法或属性
 */
class TodoBuilder {
    def level = 0
    def result = new StringWriter()

    def build(closure) {
        result << "To-DO:\n"
        System.err.println("before"+closure.delegate)//UsingTodoBuilder
        closure.delegate = this
        System.err.println("after"+closure.delegate)//TodoBuilder
        closure()
        println result
    }

    def methodMissing(String name, args) {
        handle(name, args)
    }

    def propertyMissing(String name) {
        Object[] emptyArray = []
        handle(name, emptyArray)
    }

    def handle(String name, args) {
        level++
        level.times { result << " " }
        result << placeXifStatusDone(args)
        result << name.replaceAll("_", " ")
        result << printParameters(args)
        result << "\n"

        /**
         * -1 索引 是取最后一个参数
         * 检查调用时是不是提供了 闭包
         */
        if (args.length > 0 && args[-1] instanceof Closure) {
            def theClosure = args[-1]
            System.err.println("before:"+theClosure.delegate)//UsingTodoBuilder
            theClosure.delegate = this
            System.err.println("after:"+theClosure.delegate)//TodoBuilder
            theClosure()
        }
        level--
    }

    def placeXifStatusDone(args) {
        args.length > 0 && args[0] instanceof Map && args[0]['status'] == 'done' ? "x " : "- "
    }

    def printParameters(args) {
        def values = ""
        if (args.length > 0 && args[0] instanceof Map) {
            values += " ["
            def count = 0
            args[0].each { key, value ->
                if (key == 'status') return
                count++
                //第一个参数前不用加空格
                values += (count > 1 ? " " : "")
                values += "${key}: ${value}"
            }
            values += "]"
        }
        values
    }

}

/**
 * 创建代办事项
 */
bldr = new TodoBuilder()

bldr.build {
    Prepare_Vacation(start: '02/15', end: '02/22') {
        Reserve_Flight(on: '01/01', status: 'done')
        Reserve_Hotel(on: '01/02')
        Reserve_Car(on: '01/02')
    }
    Buy_New_Mac {
        Install_QuickSilver
        Install_TextMate
        Install_Groovy {
            Run_all_tests
        }
    }
}
