/* strcnvt.c 安全的字符串转数值函数 
 * strtol() 字符串转long类型 可指定进制
 * strtoul() 字符串转unsigned long类型 可指定进制
 * 最多可以转换三十六进制 a～z都可作为数字
 * strtod() 字符串转double类型 只以十进制转换
 */
#include <stdio.h>
#include <stdlib.h>
#include "s_gets.h"
#define LIM 30

int main(void)
{
	char number[LIM];
	char *end;
	long value;
	puts("Enter a number (empty line toquit):");
	while(s_gets2(number,LIM)&&number[0]!='\0')
	{
		value=strtol(number,&end,10);//十进制 10--十进制的10 10atom -- 十进制的10
		printf("base 10 input,base 10 output:%ld,stopped at %s(%d)\n",value,end,*end);
		value=strtol(number,&end,16);//十六进制 10--十六进制的10  10atom --十六进制的10a 
		printf("base 16 input,base 10 output:%ld,stopped at %s(%d)\n",value,end,*end);
		puts("Next number:");
	}
	puts("Bye!\n");
	return 0;
}
