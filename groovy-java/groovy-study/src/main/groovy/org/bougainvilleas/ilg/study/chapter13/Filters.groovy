package org.bougainvilleas.ilg.study.chapter13

/**
 * 多个 mixin 混入
 */
abstract class Writer{
    abstract void write(String message)
}

class StringWriter extends Writer{
    def target=new StringBuilder()

    @Override
    void write(String message) {
        target.append(message)
    }
    String toString(){
        target.toString()
    }
}

def writeStuff(writer){
    writer.write("This is stupid")
    println writer
}
/**
 * 将 filters类 混入 theWriter 得到 ExpandoMetaClass 即增强后的 Writer
 * 多个类 mixin 到一个类 是保存到一个 LinkedHashSet 链表
 * 由第一个混入的开始,指向第二个混入的类,
 */
def create(theWriter,Object[] filters=[]){
    def instance=theWriter.newInstance()
    filters.each {filter->instance.metaClass.mixin filter}
    instance
}
//没有混入
writeStuff(create(StringWriter))

class UppercaseFilter{
    void write(String message){
        def allUpper=message.toUpperCase()
        invokeOnPreviousMixin(metaClass,"write",allUpper)
    }
}
/**
 * currentMixinMetaClass 当前混入的 Filter
 * methodName 获取到前一个混入的 Filter 要调用方法名
 * args 上面方法参数
 */
Object.metaClass.invokeOnPreviousMixin={
    MetaClass currentMixinMetaClass,String methodName,Object[] args->
        // delegate.getClass() == StringWriter 被混入的类
        def previousMixin = delegate.getClass()
        //mixedIn.mixinClasses 是 LinkedHashSet 先混入的在前,后混入的在后
        for(mixin in mixedIn.mixinClasses){
            if(mixin.mixinClass.theClass==currentMixinMetaClass.delegate.theClass)
                break
            previousMixin=mixin.mixinClass.theClass
        }
        // args 经过了 filter 的处理
        mixedIn[previousMixin]."$methodName"(*args)
}

println '转大写'
/**
 * StringWriter中 混入 UppercaseFilter
 * mixedIn.mixinClasses 链表内只有一个 UppercaseFilter
 */
writeStuff(create(StringWriter,UppercaseFilter))


class ProfanityFilter{
    void write(String message){
        def filtered=message.replaceAll('stupid','s*****')
        invokeOnPreviousMixin(metaClass,"write",filtered)
    }
}
println '先屏蔽敏感字'
/**
 * StringWriter中
 * 先混入 UppercaseFilter
 * 再混入 ProfanityFilter
 *
 * mixedIn.mixinClasses 链表内有两个
 * 第一个 UppercaseFilter
 * 第二个 ProfanityFilter
 */
writeStuff(create(StringWriter,UppercaseFilter,ProfanityFilter))
println '先转大写'
/**
 * StringWriter中
 * 先混入 ProfanityFilter
 * 再混入 UppercaseFilter
 *
 * mixedIn.mixinClasses 链表内有两个
 * 第一个 ProfanityFilter
 * 第二个 UppercaseFilter
 */
writeStuff(create(StringWriter,ProfanityFilter,UppercaseFilter))