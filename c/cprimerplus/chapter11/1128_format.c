/* format.c -- sprintf() 函数 stdio.h 格式化字符串
 * sprintf() 获取输入，格式化为标准形式，然后把格式化后的字符串储存在formal中
 * */
#include <stdio.h>
#include "s_gets.h"

#define MAX 20

int main(void)
{
	char first[MAX];
	char last[MAX];
	char formal[2*MAX +10];
	double prize;
	puts("Enter your first name:");
	s_gets(first,MAX);
	puts("Enter your last name:");
	s_gets(last,MAX);
	puts("Enter your prize money:");
	scanf("%lf",&prize);
	sprintf(formal,"%s,%-19s:$%6.2f\n",last,first,prize);
	puts(formal);
	return 0;
}
