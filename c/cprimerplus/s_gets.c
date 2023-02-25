/* s_gets.c 输入字符串*/
#include <stdio.h>
#include <string.h>
#include "s_gets.h"
char *s_gets(char *st,int n)
{
	char *ret_val;
	int i=0;
	ret_val=fgets(st,n,stdin);
	while(st[i]!='\n' &&st[i]!='\0')
		i++;
	if(st[i]=='\n')
		st[i]='\0';
	else
		while(getchar()!='\n')
			continue;
	return ret_val;
}

//strchr
char * s_gets2(char *st,int n)
{
	char * ret_val;
	ret_val=fgets(st,n,stdin);
	char *find;
	find=strchr(st,'\n');//如果没找到换行符，返回NULL
	if(find)
		*find='\0';
	else
		while(getchar()!='\n')
			continue;
	return ret_val;
}
