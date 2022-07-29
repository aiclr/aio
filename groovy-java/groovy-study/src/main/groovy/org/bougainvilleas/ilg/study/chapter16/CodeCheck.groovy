package org.bougainvilleas.ilg.study.chapter16


import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.ClassNode
import org.codehaus.groovy.ast.ConstructorNode
import org.codehaus.groovy.ast.FieldNode
import org.codehaus.groovy.ast.GroovyClassVisitor
import org.codehaus.groovy.ast.MethodNode
import org.codehaus.groovy.ast.PropertyNode
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.syntax.SyntaxException
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * 如果只是想找出一些常见的代码异味，可以使用 CodeNarc Groovy代码质量工具
 * 可以扩展CodeNarc 规则，以监视想检查的代码异味
 *
 * 在编译过程中，必须帮助编译器发现并应用这个 AST 变换
 * 如下使用 全局变换 的方式，此时变换可以应用于任何代码片段中，不需要代码上的任何特殊标记
 * groovy 编译器将在 classpath 中的每个JAR 文件中查找这样的全局变换
 *
 *
 * 测试方法：
 * 需要创建清单文件 声明 AST 变换的类名
 * src/main/groovy/manifest/META-INF/services/org.codehaus.groovy.transform.ASTTransformation
 * 内容 org.bougainvilleas.ilg.study.chapter16.CodeCheck
 *
 * 目录切换到 src/main/groovy下
 * 编译
 * groovyc -d classes org/bougainvilleas/ilg/study/chapter16/CodeCheck.groovy
 * 打包
 * jar -cf checkcode.jar -C classes org -C manifest .
 * 编译测试功能
 * groovyc -classpath checkcode.jar org/bougainvilleas/ilg/study/chapter16/smelly.groovy
 */


/**
 * 想使用AST 首先使用注解 @GroovyASTTransformation 告知编译器
 * groovy 编译器包含多个阶段，支持开发者在任何阶段中介入：
 *      初始化、解析、转换、语义分析、规范化、指令选择、class生成、输出、结束
 * 首个合理的时机使在语义分析阶段之后，AST在此时被创建出来
 * 想使用信息更多的AST 可以在更靠后的阶段介入
 *
 * phase = CompilePhase.SEMANTIC_ANALYSIS 指明该 AST 变换必须在语义分析阶段之后应用
 */
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class CodeCheck implements ASTTransformation {

    /**
     * 编译器到达指定阶段，将调用用于变换的类的 visit()
     * 像该方法 提供一个由 ASTNode 实例组成的列表，以及一个表示被编译diamagnetic的 SourceUnit 引用
     * 可以在 visit() 方法 内迭代这些AST节点来检查各种元素，甚至可以修改这些节点
     */
    @Override
    void visit(ASTNode[] nodes, SourceUnit source) {
        source.ast.classes.each { classNode ->
            //向从给定的源代码单元内找到的每个类节点注册一个GroovyClassVisitor 实现
            classNode.visitContents(new OurClassVisitor(source))
        }
    }
}

/**
 * AST 变换 API 提供了一个名为 GroovyClassVisitor 的访问者
 * 对于每个类节点，方法节点，字段节点等，该访问者的方法将被调用，同时被告知相应的节点信息
 * 由 API 进入类和方法
 *
 * 当导航到一个类中的类元素时，AST变换API 将调用 访问者的方法
 *
 * 对于构造器、字段、方法等，也是如此

 */
class OurClassVisitor implements GroovyClassVisitor{
    SourceUnit sourceUnit
    OurClassVisitor(theSourceUnit){sourceUnit=theSourceUnit}
    private void reportError(message,lineNumber,columnNumber){
        sourceUnit.addError(new SyntaxException(message,lineNumber,columnNumber))
    }
    /**
     * 检查方法名和方法参数名不能为单字符
     * 现在目的是发现代码异味，所以 visitMethod() 方法中检查只有一个字母的方法名，找到则添加一个错误信息
     * 编译器也相应报告该错误，并且让编译失败
     * 也可以不使用错误，将其报告未警告
     */
    void visitMethod(MethodNode node){
        if(node.name.size()==1){
            reportError("Make method name descriptive,avoid single letter names",node.lineNumber,node.columnNumber)
        }
        node.parameters.each{parameter->
            if(parameter.name.size()==1){
                reportError("Single letter parameter are morally wrong!",parameter.lineNumber,parameter.columnNumber)
            }
        }
    }
    void visitClass(ClassNode node){}
    void visitConstructor(ConstructorNode node){}
    void visitField(FieldNode node){}
    void visitProperty(PropertyNode node){}
}