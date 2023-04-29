package org.bougainvilleas.ilg.designpattern

/**
 * 装饰器模式
 * 传统写法
 */
class Logger {
    def log(String message) {
        println message
    }
}

class TimeStampingLogger extends Logger {
    private Logger logger

    TimeStampingLogger(logger) {
        this.logger = logger
    }

    def log(String message) {
        def now = Calendar.instance
        logger.log("$now.time: $message")
    }
}

class UpperLogger extends Logger {
    private Logger logger

    UpperLogger(logger) {
        this.logger = logger
    }

    def log(String message) {
        logger.log(message.toUpperCase())
    }
}

def logger = new UpperLogger(new TimeStampingLogger(new Logger()))
logger.log("G'day Mate")

logger = new TimeStampingLogger(new UpperLogger(new Logger()))
logger.log("Hi There")

/**
 * a touch of dynamic behaviour
 * 闭包三属性 this owner delegate
 * 利用 delegate 动态写法
 */
println "\n利用 delegate 动态写法"

class GenericLowerDecorator {
    private delegate

    GenericLowerDecorator(delegate) {
        this.delegate = delegate
    }

    def invokeMethod(String name, args) {
        def newargs = args.collect { arg ->
            if (arg instanceof String) {
                return arg.toLowerCase()
            } else {
                return arg
            }
        }
        delegate.invokeMethod(name, newargs)
    }
}

class GenericUpperDecorator {
    private delegate

    GenericUpperDecorator(delegate) {
        this.delegate = delegate
    }

    def invokeMethod(String name, args) {
        def newargs = args.collect { arg ->
            if (arg instanceof String) {
                return arg.toUpperCase()
            } else {
                return arg
            }
        }
        delegate.invokeMethod(name, newargs)
    }
}

class GenericTimeStampingDecorator {
    private delegate

    GenericTimeStampingDecorator(delegate) {
        this.delegate = delegate
    }

    def invokeMethod(String name, args) {
        def newargs = args.collect { arg ->
            if (arg instanceof String) {
                def now = Calendar.instance
                // 注意 GStringImpl
                println("${now.time}: $arg".class)
                println(("${now.time}: $arg" as String).class)
                return "${now.time}: $arg" as String
            } else {
                return arg
            }
        }
        delegate.invokeMethod(name, newargs)
    }
}


logger = new GenericLowerDecorator(new TimeStampingLogger(new Logger()))
logger.log('new GenericLowerDecorator(new TimeStampingLogger(new Logger()))')

logger = new GenericTimeStampingDecorator(new UpperLogger(new Logger()))
logger.log('new GenericTimeStampingDecorator(new UpperLogger(new Logger()))')

logger = new GenericUpperDecorator(new GenericTimeStampingDecorator(new Logger()))
logger.log('new GenericUpperDecorator(new GenericTimeStampingDecorator(new Logger()))')

/**
 * More dynamic decorating
 */
println "\n利用 delegate 动态写法"

class Calc {
    def add(a, b) { a + b }
}

class TracingDecorator {
    private delegate

    TracingDecorator(delegate) {
        this.delegate = delegate
    }

    def invokeMethod(String name, args) {
        println "Calling $name$args"
        def before = System.currentTimeMillis()
        def result = delegate.invokeMethod(name, args)
        println "Got $result in ${System.currentTimeMillis() - before} ms"
        result
    }
}

def traceCalc = new TracingDecorator(new Calc())
assert 15 == traceCalc.add(3, 12)

/**
 * Decorating with an Interceptor
 * 用拦截器装饰
 */
println "\n利用ProxyMetaClass和TracingInterceptor，用拦截器装饰"

class TimingInterceptor extends TracingInterceptor {
    private beforeTime

    def beforeInvoke(object, String methodName, Object[] arguments) {
        super.beforeInvoke(object, methodName, arguments)
        beforeTime = System.currentTimeMillis()
    }

    Object afterInvoke(Object object, String methodName, Object[] arguments, Object result) {
        super.afterInvoke(object, methodName, arguments, result)
        def duration = System.currentTimeMillis() - beforeTime
        writer.write("Duration: $duration ms\n")
        writer.flush()
        result
    }
}

def proxy = ProxyMetaClass.getInstance(Calc)
proxy.interceptor = new TimingInterceptor()
proxy.use {
    assert 7 == new Calc().add(1, 6)
}

/**
 * Runtime behaviour embellishment
 * 利用 ExpandoMetaClass 动态写法
 */
println "\n利用 ExpandoMetaClass 动态写法"
//下面一行 current mechanism(机制) to enable ExpandoMetaClass，注释掉也可以正常执行
GroovySystem.metaClassRegistry.metaClassCreationHandle = new ExpandoMetaClassCreationHandle()
logger = new Logger()
logger.metaClass.log = { String m -> println 'message: ' + m.toUpperCase() }
logger.log('GroovySystem.metaClassRegistry.metaClassCreationHandle=new ExpandoMetaClassCreationHandle()')



