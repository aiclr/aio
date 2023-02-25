/* put2.c -- 打印一个字符串 并统计打印的字符数 */
#include <stdio.h>
#include "myput.h"
int put2(const char *string)
{
	int count =0;
	while(*string)
	{
		putchar(*string++);
		count++;
	}
	putchar('\n');//不统计换行符
	return(count);
}
