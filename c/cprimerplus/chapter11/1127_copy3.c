/*
 *  copy3.c -- 使用strncpy() 更安全的拷贝字符串 可以指明拷贝的最大字符数i
 *  strncpy(target,source,n)
 *  把n个字符或'\0'前的字符拷贝至target中，
 *  如果source字符数小于n,拷贝整个字符串包括'\0'
 *  strncpy拷贝的字符串长度不会超过n,如果拷贝到第n个字符时还未拷贝完整个source字符串，则不会拷贝'\0'
 *  所以拷贝的副本target中不一定有空字符，因此需要把n值设置的比target数组大小少1，这样才能拷贝'\0',即确保target是字符串
 *
 *  gcc 1127_copy3.c s_gets.c
 *  */
#include <stdio.h>
#include <string.h>
#include "s_gets.h"

#define SIZE 40
#define TARGSIZE 7
#define LIM 5
int main(void)
{
	char qwords[LIM][TARGSIZE];
	char temp[SIZE];
	int i=0;
	printf("Enter %d words beginning with q:\n",LIM);
	while(i<LIM &&s_gets(temp,SIZE))
	{
		if(temp[0]!='q')
			printf("%s doesn't begin with q!\n",temp);
		else
		{
			strncpy(qwords[i],temp,TARGSIZE -1);
			qwords[i][TARGSIZE-1]='\0';
			i++;
		}
	}
	puts("HERE are the words accepted:");
	for(i=0;i<LIM;i++)
		puts(qwords[i]);
	return 0;
}
