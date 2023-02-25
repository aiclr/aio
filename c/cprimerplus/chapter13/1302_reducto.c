/* reducto.c -- 把文件压缩成原来的1/3 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define LEN 10

int main(int argc,char *argv[])
{
	FILE *in,*out;
	int ch;
	char name[LEN];//储存输出文件名
	int count=0;
	if(argc<2)
	{
		fprintf(stderr,"Usage: %s filename\n",argv[0]);
		exit(EXIT_FAILURE);
	}
	if((in=fopen(argv[1],"r"))==NULL)
	{
		fprintf(stderr,"I couldn't open the file\"%s\"\n",argv[1]);
		exit(EXIT_FAILURE);
	}
	//LEN-5 为待拼接字符 .red'\0' 预留空间 防止 argv[1] 过长，name 容量不足, 最多拷贝LEN-5个字符，多余的舍弃
	//strncpy 如果拷贝到第LEN-5个字符时还未拷贝完整个argv[1],则不会拷贝'\0',即name此时不包含'\0',不是个字符串:e
	strncpy(name,argv[1],LEN-5);//拷贝文件名
	name[LEN-5]='\0';
	strcat(name,".red");//拼接，末尾会自动加'\0'
	if((out=fopen(name,"w"))==NULL)
	{
		fprintf(stderr,"Can't create output file.\n");
		exit(EXIT_FAILURE);
	}
	while((ch=getc(in))!=EOF)
		if(count++%3==0)
			putc(ch,out);//打印三个字符中的第一个字符
	if(fclose(in)!=0||fclose(out)!=0)
		fprintf(stderr,"Error in closing files\n");
	return 0;
}
