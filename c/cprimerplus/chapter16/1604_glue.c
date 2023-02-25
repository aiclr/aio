/**
 * glue.c -- 使用##运算符
 *
 * 预处理器黏合剂 ##运算符
 * ##运算符可用于类函数宏的替换部分
 * ##还可用于对象宏的替换部分
 * ##运算符把两个记号组合成一个记号
 *
 */
#include <stdio.h>
#define XNAME(n) x##n
#define PRINT_XN(n) printf("x"#n"=%d\n",x##n)
int main(void)
{
	int XNAME(1)=14;//int x1=14;
	int XNAME(2)=20;//int x2=20;

	int x3=30;
	PRINT_XN(1);//printf("x1=%d\n",x1)
	PRINT_XN(2);//printf("x2=%d\n",x2)
	PRINT_XN(3);//printf("x3=%d\n",x3)
	return 0;
}
