/* partb.c -- 与 parta.c 一起编译 */
#include <stdio.h>
extern int count;//引用式声明，外部链接
static int total = 0;//static 内部链接 本文件内可用
void accumulate(int);

void accumulate(int k)//k block scope 块作用域，无链接
{
	static int subtotal = 0;//static 静态无链接
	if(k<=0)
	{
		printf("loop cycle: %d\n",count);
		printf("subtotal: %d; total: %d\n",subtotal,total);
		subtotal=0;
	}
	else
	{
		subtotal +=k;
		total+=k;
	}
}
