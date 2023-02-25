/* nogo.c -- strcmp()字符串比较该程序能否正常运行*/
#include <stdio.h>
#define ANSWER "Grant"
#define SIZE 40
char *s_gets(char *st,int n);
int main(void)
{
	char try[SIZE];
	puts("Who is buried in Grant's tomb?");
	s_gets(try,SIZE);
	//ANSWER 和 try 都是指针，try!=ANSWER 是比较两个指针指向的地址是否相同 很显然永远不会相同
	while (try!=ANSWER)
	{
		printf("%p\n",try);
		printf("%p\n",ANSWER);
		puts("No,that's wrong.Try again.");
		s_gets(try,SIZE);
	}
	puts("That's right!");
	return 0;
}

char *s_gets(char * st,int n)
{
	char *ret_val;
	int i=0;
	ret_val=fgets(st,n,stdin);
	while(st[i]!='\n' && st[i]!='\0')
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
