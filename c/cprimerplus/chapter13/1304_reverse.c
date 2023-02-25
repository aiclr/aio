/**
 * reverse.c -- 倒序输出文件内容
 * fseek()：定位至文件中的位置
 * 	参数
 * 		1. FILE指针指向待查找的文件(fopen()已打开的文件)
 * 		2. long类型，offset 偏移量，负=后移，0=不动，正=前移。表示从起始点开始要移动的距离
 * 		3. 确定起始点的模式，ANSI标准
 * 		   stdio.h 头文件中三个常量：(如果不确定则查阅实现的使用手册和在线帮助)
 * 		   SEEK_SET=文件开始处(旧的实现可使用0L)
 * 		   SEEK_CUR=当前位置（旧的实现可使用1L）
 * 		   SEEK_END=文件末尾（旧的实现可使用2L）
 * 	返回值：int 正常返回0,异常返回-1
 * ftell() 返回距文件开始处的字节数，以此来确定文件位置，文件的第一个字节到文件开始处的距离是0
 * 	参数: 文件指针
 * 	返回值：long 返回当前的位置
 *    	注意:适用于二进制模式打开的文件
 *
 * */
#include <stdio.h>
#include <stdlib.h>
#define CNTL_Z '\032' // DOS 文本文件中的文件结尾标记
#define SLEN 81

int main(void)
{
	char file[SLEN];
	char ch;
	FILE *fp;
	long count,last;
	puts("Enter the name of the file to be processed:");
	scanf("%80s",file);
	if((fp=fopen(file,"rb"))==NULL)//二进制只读模式打开
	{
		printf("reverse can't open %s\n",file);
		exit(EXIT_FAILURE);
	}
	fseek(fp,0L,SEEK_END);//定位到文件末尾，fp指针地址偏移到EOF
	last=ftell(fp);//获取文件末尾到文件开始处的字节数
	for(count=1L;count<=last;count++)
	{
		fseek(fp,-count,SEEK_END);//一次循环回退一个字节，即fp指针往文件头部方向移动一位（后移/左移）
		ch=getc(fp);//获取fp指针所指向的字符
		if(ch!=CNTL_Z && ch!='\r')//MS_DOS 文件
			putchar(ch);//不是换行符
//		printf("%x\n",ch);//经测试发现末尾位置是a
	}
	putchar('\n');
	fclose(fp);
	return 0;
}
