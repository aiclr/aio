/* squares.c -- 1~20的平方*/
#include <stdio.h>
int main(void)
{
	int num = 1;
	while(num < 21)
	{
		printf("%4d^2 = %6d\n",num,num * num);
		num += 1;
	}
	return 0;
}
