/**
 * gcc -lm 需要加 -lm 链接数学库参数
 * sqrt(3.0) 这种调用，gcc 在编译时就可以将其替换为对应的值（gcc 优化的一部分），从而不需要链接 sqrt 所在的数学库 libm.so
 * 而sqrt(n) 这种调用，一般无法做这种替换，因此需要链接libm.so
 *
 * 变参宏 ...和__VA_ARGS__
 * variadic.c --变参宏
 *
 * 一些函数printf() 可接受数量可变的参数
 * stdvar.h 头文件提供了工具 让用户自定义带可变参数的函数
 * C99/C11也对宏提供了这样的工具
 * 标准未使用variadic这次单词，但是它已成为描述这种工具的通用词
 * C标准的索引添加了字符串化stringizing词条，但是标准并未把固定参数的函数或宏称为固定函数和不变宏
 */
#include <stdio.h>
#include <math.h>
#define PR(X,...) printf("Message "#X" : "__VA_ARGS__)
int main(void)
{
	double x=48;
	double y;
	y=sqrt(x);
	PR(1,"x=%g\n",x);
	PR(2,"x=%.2f,y=%.4f\n",x,y);
	return 0;
}
