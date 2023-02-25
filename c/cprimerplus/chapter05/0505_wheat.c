/* wheat.c -- 指数函数 2^x x[1,55] */
#include <stdio.h>
int main(void)
{
	printf("%f\n",2E2);
	printf("%E\n",200);
	double total,y;//注意数据类型 %E 会将底层数据转换为浮点型
	total = y = 1.0;
	printf("x=%d y=%.2e total=%.2E\n",1,y,total);
	int x = 2;
	while(x <= 55)
	{
		y *= 2.0;
		total += y;
		printf("x=%d y=%.2e total=%.2E\n",x,y,total);
		x += 1;
	}
}
