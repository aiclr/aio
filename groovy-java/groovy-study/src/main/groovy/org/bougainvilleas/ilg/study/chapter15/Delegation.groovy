package org.bougainvilleas.ilg.study.chapter15

class Worker {
    def simpleWork1(spec) { println "worker does work1 with spec $spec" }

    def simpleWork2() { println 'worker does work2' }
}

class Expert {
    def advancedWork1(spec) { println "Expert does work1 with spec $spec" }

    def advancedWork2(scope, spec) {
        println "Expert does work2 with scope $scope spec $spec"
    }
}

class Manager {
    def worker = new Worker()
    def expert = new Expert()

    def schedule() { println "Scheduling ..." }

    def methodMissing(String name, args) {
        println "methodMissing call to $name..."
        //先清空 delegateTo
        def delegateTo = null
        if (name.startsWith('simple')) {
            delegateTo = worker
        }
        if (name.startsWith('advanced')) {
            delegateTo = expert
        }
        /**
         * {@link org.bougainvilleas.ilg.study.chapter11.UsingMetaMethod}
         * hasProperty() 检查属性是否存在
         * respondsTo() 检查方法是否存在 接收参数 要查询的实例，要查询的方法名，以及以逗号分隔的提供给该方法的参数
         * 返回 MetaMethod 列表，其中包括匹配的方法
         *
         * ?.操作符只有对象引用不为空时才会分派调用 避免空指针
         */
        if (delegateTo?.metaClass?.respondsTo(delegateTo, name, args)) {
            Manager instance = this
            //将不存在方法添加给 this的 metaClass
            instance.metaClass."$name" = { Object[] varArgs ->
                delegateTo.invokeMethod(name, varArgs)
            }
            //调用 方法
            delegateTo.invokeMethod(name, args)
        } else {
            throw new MissingMethodException(name,Manager.class,args)
        }
    }
}


peter=new Manager()
peter.schedule()
peter.simpleWork1('fast')
peter.simpleWork1('quality')//调用 metaClass 内合成的新方法，不再调用 methodMissing
peter.simpleWork2()
peter.simpleWork2()
peter.advancedWork1('fast')
peter.advancedWork1('quality')
peter.advancedWork2('prototype','fast')
peter.advancedWork2('product','quality')

try{
    peter.simpleWork3()
}catch(ex)
{
    println ex
}