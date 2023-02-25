/* quit_chk.c -- 使用strcmp() 检测程序是否要停止读取输入 */
#include <stdio.h>
#include <string.h>
#define SIZE 80
#define LIM 10
#define STOP "quit"
char *s_gets(char *st,int n);
int main(void)
{
	char input[LIM][SIZE];
	int ct =0;
	printf("Enter up to %d lines (type quit to quit):\n",LIM);
	//input[ct][0] != '\0' 检测是否输入空行 输入空行结束
	while(ct<LIM && s_gets(input[ct],SIZE) !=NULL && input[ct][0] !='\0' && strcmp(input[ct],STOP))
		ct++;
	printf("%d strings entered\n",ct);
	return 0;
}

char *s_gets(char *st,int n)
{
	char *ret_val;
	int i=0;
	ret_val=fgets(st,n,stdin);
	while(st[i] != '\n' && st[i] != '\0')
		i++;
	if(st[i]=='\n')
		st[i]='\0';
	else
	{
		while(getchar()!='\n')
			continue;
	}
	return ret_val;
}
