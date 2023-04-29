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
 * 利用delegate 动态写法
 */
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


logger =new GenericLowerDecorator(new TimeStampingLogger(new Logger()))
logger.log('new GenericLowerDecorator(new TimeStampingLogger(new Logger()))')

logger =new GenericTimeStampingDecorator(new UpperLogger(new Logger()))
logger.log('new GenericTimeStampingDecorator(new UpperLogger(new Logger()))')

logger =new GenericUpperDecorator(new GenericTimeStampingDecorator(new Logger()))
logger.log('new GenericUpperDecorator(new GenericTimeStampingDecorator(new Logger()))')


/**
 * Runtime behaviour embellishment
 */
//current mechanism(机制) to enable ExpandoMetaClass
//GroovySystem.metaClassRegistry.metaClassCreationHandle=new ExpandoMetaClassCreationHandle()
logger=new Logger()
logger.metaClass.log={String m-> println 'message: '+m.toUpperCase()}
logger.log('Runtime behaviour embellishment')