/* shoes1.c -- 把鞋码转换成英寸*/
#include <stdio.h>
#define ADJUST 7.31 // 常量
int main(void)
{
	const double SCALE = 0.333;//const 变量
	double shoe,foot;
	shoe = 3.0;
	printf("Shoe size (men's) foot length\n");
	while(shoe < 18.5)
	{
		foot = SCALE * shoe + ADJUST;
		printf("%10.1f %15.2f inches\n",shoe,foot);
		shoe += 1.0;
	}
	return 0;
}
