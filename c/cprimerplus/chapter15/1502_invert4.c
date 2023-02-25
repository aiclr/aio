/**
 * invert4.c -- 使用位操作显示二进制
 * 切换一个值中的后n位，待处理值和n都是函数的参数
 */
#include <stdio.h>
#include <limits.h>
char *itobs(int,char *);
void show_bstr(const char *);
int invert_end(int num,int bits);
int main(void)
{
	char bin_str[CHAR_BIT*sizeof(int)+1];
	int number;
	puts("Enter integers and see them in binary.");
	puts("Non-numeric input terminates program.");
	while(scanf("%d",&number)==1)
	{
		itobs(number,bin_str);
		printf("%d is\n",number);
		show_bstr(bin_str);
		putchar('\n');
		number=invert_end(number,4);
		printf("Inverting the last 4 bits gives\n");
		show_bstr(itobs(number,bin_str));
		putchar('\n');
	}
	puts("Bye!");
	return 0;
}

/**
 * 将n的每一位与1进行&运算（不变）
 * n从int 转换成二进制格式的字符串
 */
char *itobs(int n,char *ps)
{
	int i;
	const static int size=CHAR_BIT*sizeof(int);
	for(i=size-1;i>=0;i--,n>>=1)
		ps[i]=(01&n)+'0';
	ps[size]='\0';
	return ps;
}

void show_bstr(const char *str)
{
	int i=0;
	while(str[i])
	{
		putchar(str[i]);
		if(++i %4==0 && str[i])
			putchar('\t');
	}
}

/**
 * num的后bits位 与mask 异或运算 ^ （反转）
 * bits 是位数
 */
int invert_end(int num,int bits)
{
	int mask=0;
	int bitval=1;
	//mask全部转换成1,
	while(bits-->0)
	{
		mask |= bitval;
		bitval <<=1;
	}
	return num^mask;
}
