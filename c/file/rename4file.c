/* rename4file.c -- 在文件名前增加一个字符0 cpp文件夹修改文件名，在文件名前加0*/
#include <stdio.h>
#include <string.h>
#include <fcntl.h>
#define ZERO "0"
int main(int argc, char *argv[])
{
	char newname[100];
	//拷贝字符串
	strcpy(newname,argv[1]);
	char zero[100]="0";
	//拼接字符串
	strcat(zero,newname);
	if(rename(argv[1],zero))
		printf("%s to %s",argv[1],zero);
	else
		perror("rename");
	return 0;
}
