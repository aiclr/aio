/* hiding.c -- block variable 块变量*/
#include <stdio.h>
int main(void)
{
	int x=30;//原始x
	printf("x is outer block: %d at %p\n",x,&x);
	{
		int x=77;
		printf("x is inner block: %d at %p\n",x,&x);
	}
	printf("x is outer block: %d at %p\n",x,&x);
	while(x++<33)
	{
		int x =100;
		x++;
		printf("x in while loop: %d at %p\n",x,&x);
	}
	printf("x is outer block: %d at %p\n",x,&x);
	return 0;
}
