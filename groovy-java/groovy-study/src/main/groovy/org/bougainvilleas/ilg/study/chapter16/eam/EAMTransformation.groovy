package org.bougainvilleas.ilg.study.chapter16.eam

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.Parameter
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 *
 * 通过AST 变换想类中注入方法和字段，无需第三方库，可在编译时实现AOP
 * 编译时 实现 use() 方法。不需要程序员手动实现use()
 * 参考4.5节 手动实现 use() {@link org.bougainvilleas.ilg.study.chapter04.Resource}
 *
 * 本例为 局部变换 该变换只应用于程序员使用注解标记的特定类
 * 局部变换的不必创建额外的清单文件
 *
 * 将静态 use() 插入到被注解的类中
 * 因为变换只在目标类上调用，可以直接在 visit() 方法中处理
 *
 * EAM模式 核心 注入到类中的特殊的 use() 方法
 * 在此方法中，需要将实例发送给一个使用该实例的闭包
 * 闭包调用本身需要包在一个 try-finally 块中
 * finally 块中将调用清理代码。
 *
 *
 */
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class EAMTransformation implements ASTTransformation {
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {

        astNodes.findAll { node -> node instanceof ClassNode }.each { classNode ->

            /**
             * 使用 ASTBuilder 的buildFromCode() f方法来创建 use() 方法
             * 并将其注入到类中
             * 假设目标有一个 open() 一个 close() 方法
             * 如果缺少这两个方法将抛出运行时异常
             *
             * 也可以抛出编译时错误
             *
             * 必须遍历该类的AST 节点，如果没有找到，像 16.1 报告错误
             */
            def useMethodBody = new AstBuilder()
                    .buildFromCode {//创建 use() 方法的方法体
                        def instance = newInstance()
                        try {
                            instance.open()
                            instance.with block
                        } finally {
                            instance.close()
                        }
                    }

            //将 use() 方法体附到一个表示 use() 方法的方法节点上
            def useMethod = new MethodNode(
                    'use', //方法名
                    groovyjarjarasm.asm.Opcodes.ACC_PUBLIC | groovyjarjarasm.asm.Opcodes.ACC_STATIC,//使用 public static 修饰符
                    org.codehaus.groovy.ast.ClassHelper.OBJECT_TYPE,//返回类型 Object
                    [new Parameter(org.codehaus.groovy.ast.ClassHelper.OBJECT_TYPE, 'block')] as Parameter[],//该方法接受的参数 use 接收一个闭包
                    [] as ClassNode[],//该方法可能抛出的异常
                    useMethodBody[0])//方法体

            //将组装好的 use 方法 添加到 目标类中
            classNode.addMethod(useMethod)
        }
    }
}