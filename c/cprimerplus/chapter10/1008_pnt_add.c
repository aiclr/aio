/* put_add.c -- 指针地址 */
#include <stdio.h>
#define SIZE 4

int main(void)
{
	short dates[SIZE];
	short * pti;
	short index;
	double bills[SIZE];
	double * ptf;
	pti=dates;
	ptf=bills;
	printf("%23s %15s\n","short","double");
	for(index=0;index<SIZE;index++)
	{
		printf("pointers + %d: %p + %d = %10p, %p +%d = %10p\n",index,&dates[index],index,&dates[index]+index,&bills[index],index,&bills[index]+index);
	}
	return 0;
}
