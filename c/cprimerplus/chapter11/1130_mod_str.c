/* mod_str.c -- 修改字符串 */
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "s_gets.h"

#define LIMIT 81
void ToUpper(char *);
int PunctCount(const char*);
int main(void)
{
	char line[LIMIT];
	puts("Please enter a line:");
	s_gets2(line,LIMIT);
	ToUpper(line);
	puts(line);
	printf("That line has %d punctuation character.\n",PunctCount(line));
	return 0;
}

void ToUpper(char * str)
{
	while(*str)
	{
		//toupper 转大写
		*str=toupper(*str);
		str++;
	}
}

int PunctCount(const char * str)
{
	int ct=0;
	while(*str)
	{
		//ispunct 统计标点符号
		if(ispunct(*str))
			ct++;
		str++;
	}
	return ct;
}
