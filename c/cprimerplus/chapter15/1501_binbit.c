/**
 * binbit.c -- 使用位操作显示二进制
 */
#include <stdio.h>
#include <limits.h> //提供CHAR_BIT的定义，CHAR_BIT 表示每字节的位数
char *itobs(int,char *);
void show_bstr(const char*);

int main(void)
{
	char bin_str[CHAR_BIT * sizeof(int)+1];
	int number;
	puts("Enter integers and see them in binary.");
	puts("Non-numeric input terminates program.");
	while(scanf("%d",&number)==1)
	{
		itobs(number,bin_str);
		printf("%d is ",number);
		show_bstr(bin_str);
		putchar('\n');
	}
	puts("Bye!");
	return 0;
}

char *itobs(int n, char *ps)
{
	int i;
	const static int size=CHAR_BIT*sizeof(int);
	for(i=size-1;i>=0;i--,n>>=1)//n每次循环右移一位
	{
		//掩码01 & n 得最低位值 与char '0'相加（相当于&）
		ps[i]=(01&n)+'0';
		//putchar(ps[i]);
		//putchar('\n');
	}
	ps[size]='\0';
	return ps;
}

void show_bstr(const char* str)
{
	int i=0;
	while(str[i])
	{
		putchar(str[i]);
		if(++i%4==0 && str[i])
			putchar(' ');
	}
}
