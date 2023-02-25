/* hello.c -- 命令行参数转数字 
 *
 * stdlib.h
 * atoi()
 * atoi("42regular") 返回42
 * atoi("what") 返回0 但是C标准规定这种情况下的行为是未定义的，不安全
 *
 * 类似的函数有 都不安全
 * atoi() 把字符串转换成int
 * atof() 把字符串转换成double类型
 * atol() 把字符串转换成long类型
 * */
#include <stdio.h>
#include <stdlib.h>
int main(int argc,char *argv[])
{
	int i,times;
	if(argc<2||(times=atoi(argv[1]))<1)
		printf("Usage:%s positive-number\n",argv[0]);
	else
		for(i=0;i<times;i++)
			puts("Hello,good looking!");
	return 0;
}
