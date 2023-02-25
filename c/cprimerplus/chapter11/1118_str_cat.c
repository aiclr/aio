/* str_cat.c -- 拼接字符串*/
#include <stdio.h>
#include <string.h>
#define SIZE 80
char *s_gets(char *st,int n);
int main(void)
{
	char flower[SIZE];
	char addon[] ="s smell like old shoes.";
	puts("What is your favorite flower?");
	if(s_gets(flower,SIZE))
	{
		//flower 拼接上addon flower变化，addon未变
		printf("拼接前flower地址%p\n",flower);
		strcat(flower,addon);
		printf("拼接后flower地址%p\n",flower);
		puts(flower);
		puts(addon);
	}
	else
		puts("End of the encountered!");
	puts("bye");
	return 0;
}

char *s_gets(char *st,int n)
{
	char *ret_val;
	int i=0;
	ret_val=fgets(st,n,stdin);
	if(ret_val)
	{
		while(st[i]!='\n' && st[i] !='\0')
			i++;
		if(st[i]=='\n')
			st[i]='\0';
		else
			while(getchar()!= '\n')
				continue;
	}
	return ret_val;
}
