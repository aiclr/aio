/* file_eof.c -- 打开一个文件并显示该文件 */
#include <stdio.h>
#include <stdlib.h>
int main(void)
{
	int ch;
	FILE *fp;
	char fname[50];
	printf("Enter the name of the file: ");
	scanf("%s",fname);
	fp = fopen(fname,"r");//以只读模式打开文件
	if(fp == NULL)
	{
		printf("Failed to open file.Bye\n");
		exit(1);//退出程序
	}
	while((ch=getc(fp))!=EOF)
		putchar(ch);
	fclose(fp);//关闭文件
	return 0;
}
