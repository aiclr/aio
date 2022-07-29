package org.bougainvilleas.ilg.study.chapter16.smell
/**
 * canVote() 方法接收一个表示年龄的参数，确定这个人是否可以投票
 * 但是使用只有一个字母的变量名是不符合编程规范的
 * 古怪的方法名 p() 也不符合规范
 * 这部分代码语法上正确，但是有异味，编译器不会检查代码异味，
 * 使用编译时元编程来检查代码异味，发现代码中的异味和不良实践，使编码符合规范，
 */
def canVote(a){
    a>17?"You can vote":"You can't vote"
}
def p(instance){
    //用于打印实例的代码
}