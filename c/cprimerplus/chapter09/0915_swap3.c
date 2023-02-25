/* swap3.c -- swap1.c 没有修改到main函数内的变量，因为修改的是局部变量 */
#include <stdio.h>
void interchange(int *,int *); //函数原型

int main(void)
{
	int x = 5,y = 6;
	printf("Originally x = %d and y = %d.\n",x,y);
	interchange(&x,&y);
	printf("Now x = %d and y = %d.\n",x,y);
	return 0;
}

void interchange(int *u,int *v)
{
	*u = *u ^ *v;
	*v = *u ^ *v;
	*u = *u ^ *v;
}
