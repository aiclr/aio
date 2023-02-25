/* proto.c -- 使用函数原型 */
#include <stdio.h>
int imax(int,int);
int main(void)
{
	//printf("The maximum of %d and %d is %d.\n",3,5,imax(3));
  printf("The maximum of %d and %d is %d.\n",3,5,imax(3.9,5.4));
	printf("The maximum of %d and %d is %d.\n",3,5,imax(3,5));
	return 0;
}

int imax(int n,int m)
{
	return n>m?n:m;
}
