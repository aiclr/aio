/* swap1.c -- 第一个版本的交换函数 */
#include <stdio.h>

void interchange(int,int);//函数原型

int main(void)
{
	int x =5,y=6;
	printf("Originally x = %d and y = %d.\n",x,y);
	interchange(x,y);
	printf("Now x = %d and y = %d.\n",x,y);
	return 0;
}

//异或运算 不引入第三个变量 
void interchange(int u,int v)
{
	printf("%d %d\n",u,v);
	u = u ^ v;
	v = u ^ v;
	u = u ^ v;
	printf("%d %d\n",u,v);
}
