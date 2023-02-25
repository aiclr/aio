/* designate.c -- 使用指定初始化器 */
#include <stdio.h>
#define MONTHS 12
int main(void)
{
	int days[MONTHS] = {31,28,[4]=31,30,31,[1]=29};//使用初始化器只初始化数组中的最后一个元素
	for(int i = 0;i<MONTHS;i++)
	{
		printf("%2d %d \n",i+1,days[i]);
	}
	return 0;
}
