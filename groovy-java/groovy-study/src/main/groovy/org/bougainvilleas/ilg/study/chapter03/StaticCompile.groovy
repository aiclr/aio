package org.bougainvilleas.ilg.study.chapter03

import groovy.transform.CompileStatic

/**
 * groovy 元编程和动态类型的优点显而易见
 * 但是这些优点 需要以性能为代价
 * 性能的下降与代码,所调用方法的个数等因素有关
 * 不需要元编程和动态能力时,与等价的java代码相比,性能损失可能高达10%
 * java7 的InvokeDynamic 特性就旨在缓解这种问题
 * 但是对于使用老版本java的人而言,静态编译可能是个有用的特性
 *
 * 关闭动态类型,阻止元编程,放弃多方法,让Groovy生成性能足以与java媲美的高效字节码
 *
 * @CompileStatic 注解让groovy 执行静态编译
 * 为目标代码生成的字节码会和javac 生成的字节码很像
 *
 */

/**
 * 编译后 使用javac 查看字节码
 * idea 使用jclasslib 查看
 *
 * java8
 * javac -p StaticCompile
 */


/**
 *
 0 nop
 1 invokestatic #15 <org/bougainvilleas/ilg/study/chapter03/StaticCompile.$getCallSiteArray : ()[Lorg/codehaus/groovy/runtime/callsite/CallSite;>
 4 astore_2
 5 aload_2
 6 ldc #44 <1>
 8 aaload
 9 aload_0
 10 aload_2
 11 ldc #45 <2>
 13 aaload
 14 aload_1
 15 invokeinterface #49 <org/codehaus/groovy/runtime/callsite/CallSite.call : (Ljava/lang/Object;)Ljava/lang/Object;> count 2
 20 invokeinterface #53 <org/codehaus/groovy/runtime/callsite/CallSite.callCurrent : (Lgroovy/lang/GroovyObject;Ljava/lang/Object;)Ljava/lang/Object;> count 3
 25 areturn
 * 非静态编译 toUpperCase() 方法 使用 CallSite() 调用, 它会处理groovy的动态调用机制
 */
def shout1(String str){
    println str.toUpperCase()
}

/**
 * 静态编译的与java编译器所做的一样
 0 aload_0
 1 checkcast #2 <org/bougainvilleas/ilg/study/chapter03/StaticCompile>
 4 aload_1
 5 invokevirtual #62 <java/lang/String.toUpperCase : ()Ljava/lang/String;>
 8 invokevirtual #66 <org/bougainvilleas/ilg/study/chapter03/StaticCompile.println : (Ljava/lang/Object;)V>
 11 aconst_null
 12 areturn
 */
@CompileStatic
def shout2(String str){
    println str.toUpperCase()
}
