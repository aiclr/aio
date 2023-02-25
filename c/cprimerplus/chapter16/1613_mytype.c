/**
 * #line #error
 * #line 指令重置 __LINE__ 和 __FILE__ 宏报告的行号和文件名
 *
 * #line 1000  //把当前行号重置为1000
 * #line 10 "cool.c" //把行号重置为10,把文件名重置为cool.c
 * 
 * #error 指令让预处理器发出一条错误消息，该消息包含指令中的文本。如果可能的话编译过程应该中断
 * #if __STDC_VERSION__!=201112L
 * #error Not C11
 * #endif
 * 编译以上代码生成后，输出如下
 * $gcc newish.c
 * newish.c:14:2:error:#error Not C11
 * $gcc -std=c11 newish.c
 * $
 * 如果编译器只支持旧标准，则会编译失败，如果支持c11标准就能成功编译
 *
 * #pragma 把编译器指令放入源代码中
 * 如在开发C99时，标准被成为C9X #pragma c9x on
 * 编译器都有自己的编译指示集，C99提供了三个标准编译指示
 * c99提供了_Pragma预处理器运算符，该运算符把字符串转换成普通的编译指示
 * 把字符串中的转译序列转换成它所代表的字符
 * _Pragma("nonstandardtreatmenttypeB on")
 * 等价于
 * #pragma nonstandardtreatmenttypeB on
 *
 * _Pragma("use_tool \"true \"false")
 * #pragma use_tool "true" false
 *
 * 泛型选择C11
 * 泛型编程 generic programming 指那些没有特定类型，但是一旦指定一种类型，就可以转换成指定类型的代码
 * C11增加泛型选择表达式 generic selection expression,可根据表达式的类型（即表达式的类型是int、double还是其他类型）选择一个值
 * 泛型表达式不是预处理器指令，但是在一些泛型编程中它常用作#define宏定义的一部分
 *
 * _Generic(x,int:0,float:1,double:2,default:3)
 * 第一项x是表达式
 * 后面每一项都由一个类型、一个冒号、一个值组成
 * 第一项的类型匹配到哪个标签，整个表达式的值是该标签后面的值
 * 如上x是int类型变量，x匹配int,那么整个表达式的值就是0
 * 如果没有与类型匹配的标签，表达式就是default标签后面的值，类似switch
 * _Generic用表达式的类型匹配标签
 * switch用表达式的值匹配标签
 *
 *
 */
#if __STDC_VERSION__!=201112L
#error Not C11
#endif

#include <stdio.h>
#define MYTYPE(X) _Generic((X),\
int: "int",\
float: "float",\
double: "double",\
default: "other"\
)
int main(void)
{
	int d=5;
	printf("%s\n",MYTYPE(d));//int
	printf("%s\n",MYTYPE(2.0*d));//double
	printf("%s\n",MYTYPE(3L));//long
	printf("%s\n",MYTYPE(&d));//&d 类型 int*
	return 0;
}
