package org.bougainvilleas.ilg.study.chapter16.inject

import org.codehaus.groovy.ast.ASTNode
import org.codehaus.groovy.ast.builder.AstBuilder
import org.codehaus.groovy.ast.expr.ArgumentListExpression
import org.codehaus.groovy.ast.expr.MethodCallExpression
import org.codehaus.groovy.ast.expr.VariableExpression
import org.codehaus.groovy.ast.stmt.ExpressionStatement
import org.codehaus.groovy.ast.stmt.Statement
import org.codehaus.groovy.control.CompilePhase
import org.codehaus.groovy.control.SourceUnit
import org.codehaus.groovy.transform.ASTTransformation
import org.codehaus.groovy.transform.GroovyASTTransformation

/**
 * groovy/org/bougainvilleas/ilg/study/chapter16/inject
 * groovyc -d classes InjectAudit.groovy
 * jar -cf injectAudit.jar -C classes org -C manifest .
 *
 * groovy UsingCheckingAccount.groovy
 depositing 1000...
 depositing 12000...
 withdrawing 11000...
 * groovy -classpath injectAudit.jar UsingCheckingAccount.groovy
 depositing 1000...
 depositing 12000...
 withdrawing 11000...
 */
@GroovyASTTransformation(phase = CompilePhase.SEMANTIC_ANALYSIS)
class InjectAudit implements ASTTransformation {
    void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
        println 'astNodes: '+astNodes
        println 'astNodes: '+astNodes[0]
        println 'astNodes: '+astNodes[0].classes

        def checkingAccountClassNode =
                astNodes[0].classes.find { it.name=='org.bougainvilleas.ilg.study.chapter16.inject.CheckingAccount' }

        injectAuditMethod(checkingAccountClassNode)
    }

    static void injectAuditMethod(checkingAccountClassNode) {
        println 'checkingAccountClassNode: '+checkingAccountClassNode

        //非 audit 方法
        def nonAuditMethods =
                checkingAccountClassNode?.methods.findAll { it.name != 'audit' }
        println 'nonAuditMethods: '+nonAuditMethods
        nonAuditMethods?.each { injectMethodWithAudit(it) }
    }

    static void injectMethodWithAudit(methodNode) {
        def callToAudit = new ExpressionStatement(
                new MethodCallExpression(
                        new VariableExpression('this'),
                        'audit',
                        new ArgumentListExpression(methodNode.parameters)
                )
        )

        methodNode.code.statements.add(0, callToAudit)
    }

    static void injectMethodWithAudit2(methodNode) {
        List<Statement> statements = new AstBuilder().buildFromSpec {
            expression {
                methodCall {
                    variable 'this'
                    constant 'audit'
                    argumentList {
                        methodNode.parameters.each { variable it.name }
                    }
                }
            }
        }
        def callToAudit=statements[0]
        methodNode.code.statements.add(0,callToAudit)
    }

    static void injectMethodWithAudit3(methodNode) {
        def codeAsString = 'audit(amount)'
        List<Statement> statements = new AstBuilder().buildFromString(codeAsString)

        def callToAudit = statements[0].statements[0].expression
        methodNode.code.statements.add(0, new ExpressionStatement(callToAudit))
    }

    static void injectMethodWithAudit4(methodNode) {
        println 'methodNode: '+methodNode
        List<Statement> statements = new AstBuilder().buildFromCode { audit(amount) }
        def callToAudit = statements[0].statements[0].expression
        println 'callToAudit: '+callToAudit
        methodNode.code.statements.add(0, new ExpressionStatement(callToAudit))
    }



}