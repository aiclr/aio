/* count.c -- 使用标准IO 统计文件中的字符数*/
#include <stdio.h>
//提供exit() 原型 关闭所有打开的文件并结束程序
//0或EXIT_SUCCESS 表明成功结束程序
//EXIT_FAILURE 表明结束程序失败
#include <stdlib.h>

int main(int argc,char *argv[])
{
	int ch;
	FILE *fp;
	unsigned long count = 0;
	if(argc!=2)
	{
		printf("Usage:%s filename\n",argv[0]);
		exit(EXIT_FAILURE);
	}
	if((fp=fopen(argv[1],"r"))==NULL)
	{
		printf("Can't open %s\n",argv[1]);
		exit(EXIT_FAILURE);
	}
	while((ch=getc(fp))!=EOF)
	{
		putc(ch,stdout);//与putchar(ch);相同
		count++;
	}
	fclose(fp);
	printf("File %s has %lu characters\n",argv[1],count);
	return 0;
}
