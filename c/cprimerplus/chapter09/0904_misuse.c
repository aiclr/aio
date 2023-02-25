/* misuse.c -- 错误的使用函数 */
#include <stdio.h>
int imax(); //旧版函数声明

int main(void)
{
	printf("The maximum of %d and %d is %d.\n",3,5,imax(3));
	printf("The maximum of %d and %d is %d.\n",3,5,imax(3.0,5.0));
	return 0;
}

int imax(n,m)
	int n ,m;
{
	return n>m?n:m;
}
