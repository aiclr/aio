/**
 * assert() 表达式是在运行时进行检查
 * C11 新增特性 _Static_assert声明,可以在编译时检查assert() 表达式
 * 因此,assert() 可以导致正在运行的程序中止
 * _Static_assert() 可以导致程序无法通过编译
 *
 * _Static_assert() 接受两个参数
 * 第一个参数是整型常量表达式
 * 第二个参数是一个字符串
 * 如果第一个表达式求值为0或_False,编译器会显示字符串,而且不编译该程序
 * 
 * _Static_assert要求第一个参数是整型常量表达式,这保证了能在编译器求值(sizeof表达式被视为整型常量)
 */
#include <stdio.h>
#include <limits.h>
_Static_assert(CHAR_BIT==16,"16-bit char falsely assumed");
int main(void)
{
	puts("char is 16 bits.");
	return 0;
}
