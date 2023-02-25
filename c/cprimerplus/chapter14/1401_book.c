/**
 * book.c -- 一本书的图书目录
 */
#include <stdio.h>
#include <string.h>
char * s_gets(char * st, int n);
#define MAXTITLE 41//书名+1
#define MAXAUTL 31//作者名+1
struct book
{
	char title[MAXTITLE];
	char author[MAXAUTL];
	float value;
};//分号必须有

int main(void)
{
	struct book library;
	printf("Please enter the book title.\n");
	s_gets(library.title,MAXTITLE);
	printf("Now enter the author.\n");
	s_gets(library.author,MAXAUTL);
	printf("Now enter the value.\n");
	scanf("%f",&library.value);
	printf("%s by %s:%.2f\n",library.title,library.author,library.value);
	printf("%s: \"%s\"($%.2f)\n",library.author,library.title,library.value);
	printf("Done.\n");
	return 0;
}
char * s_gets(char * st,int n)
{
	char * ret_val;
	char *find;
	ret_val=fgets(st,n,stdin);
	if(ret_val)
	{
		find=strchr(st,'\n');//查找换行符
		if(find)
			*find = '\0';//换行符替换为字符串结尾符
		else
			while(getchar()!='\n')//消耗掉剩余字符串
				continue;
	}
	return ret_val;
}
