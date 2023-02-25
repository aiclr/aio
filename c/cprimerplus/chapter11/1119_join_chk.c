/* join_chk.c -- 拼接两个字符串 检查第一个数组的大小*/
#include <stdio.h>
#include <string.h>
#define SIZE 30
#define BUGSIZE 13
char *s_gets(char *st,int n);
int main(void)
{
	char flower[SIZE];
	char addon[]="s smell like old shoes.";
	char bug[BUGSIZE];
	int available;
	puts("What is your favorite flower?");
	s_gets(flower,SIZE);
	//strcat 如果flower空间不够大，拼接后多出来的字符溢出到相邻存储单元时会出问题，
	//缓冲区溢出
	if((strlen(addon)+strlen(flower)+1)<=SIZE)
		strcat(flower,addon);
	puts(flower);
	puts("What is your favorite bug?");
	s_gets(bug,BUGSIZE);
	available=BUGSIZE-strlen(bug)-1;
	//strncat() 将addon附加到bug,在加到available个字符或遇到空字符时停止
	strncat(bug,addon,available);
	puts(bug);
	return 0;
}

char *s_gets(char *st,int n)
{
	char*ret_val;
	int i=0;
	ret_val=fgets(st,n,stdin);
	if(ret_val)
	{
		while(st[i]!='\n' && st[i]!='\0')
			i++;
		if(st[i]=='\n')
			st[i]='\0';
		else
		{
			while(getchar()!='\n')
				continue;
		}
	}
	return ret_val;
}
