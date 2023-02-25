/**
 * #if #elif指令
 * 新的编译器提供新的方法测试名称是否已定义，#if defined(VAX) 代替 #ifdef VAX
 * defined 是一个预处理器运算符，如果他的参数是用#define 定义过，则返回1,否则返回0
 * 有点是可以和#elif 一起使用
 *
 * #if SYS==1
 * #include "ibm.h"
 * #endif
 *
 * #if SYS==1
 * #include "ibmpc.h"
 * #elif SYS==2
 * #include "vax.h"
 * #elif SYS==3
 * #include "mac.h"
 * #else
 * #include "general.h"
 * #endif
 *
 * defined 改写
 *
 * #if defined (IBMPC)
 * #include "ibm.h"
 * #elif defined (VAX)
 * #include "vax.h"
 * #elif defined (MAC)
 * #include "mac.h"
 * #else
 * #include "general.h"
 * #endif
 *
 * predef.c 需要支持C99的编译器 gcc -std=c99或-std=c11 predef.c
 * C标准规定了一些预定义宏
 * 	C99标准提供__func__预定义标识符，展开为一个代表函数名的字符串（该函数包含该标识符）
 * 	__func__必须具有函数作用域，而从本质上看宏具有文件作用域，因此__func__是C语言的预定义标识符，而不是预定义宏
 *
 * __STDC__ 设置为1 表明实现遵循C标准
 * __STDC_HOSTED__ 本机环境设置为1 否则设置为0
 */

#include <stdio.h>
void why_me();
int main(void)
{
	printf("The file is %s.\n",__FILE__);//表示当前源代码文件名的字符串字面量
	printf("The date is %s.\n",__DATE__);//预处理日期（Mmm dd yyyy）字符串字面量 Feb 26 2022
	printf("The time is %s.\n",__TIME__);//翻译代码的时间 hh:mm:ss
	printf("The version is %ld.\n",__STDC_VERSION__);//支持C99标准 设置为199901L,支持C11标准设置为201112L，测试发现为201710
	printf("This is line %d.\n",__LINE__);//表示当前源代码文件中行号的整型常量
	printf("This function is %s.\n",__func__);//预定义标识符
	why_me();
	return 0;
}
void why_me()
{
	printf("This function is %s.\n",__func__);
	printf("This is line %d.\n",__LINE__);
}
	
