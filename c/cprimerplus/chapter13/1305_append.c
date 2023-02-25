/**
 * append.c -- 把文件附加到另一个文件末尾
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define BUFSIZE 4096
#define SLEN 81

void append(FILE * source,FILE * dest);
char * s_gets(char * st,int n);

int main(void)
{
	FILE *fa,*fs;//fa指向目标文件，fs指向源文件
	int files=0;//附加的文件数量
	char file_app[SLEN];//目标文件名
	char file_src[SLEN];//源文件名
	int ch;
	puts("Enter name of destination file:");
	s_gets(file_app,SLEN);//输入目标文件名
	if((fa=fopen(file_app,"a+"))==NULL)//a+ 读写模式，末尾追加，不存在则新建
	{
		fprintf(stderr,"Can't open %s\n",file_app);
		exit(EXIT_FAILURE);
	}
	/**
	 * int setvbuf(FILE * restrict fp,char * restrice buf,int mode,size_t size)
	 * 打开文件后，未对流进行其他操作之前，调用该函数
	 * 创建一个供标准io函数替换使用的缓冲区
	 * fp: 识别待处理的流
	 * buf: 指向待使用的存储区（NULL时，会自己分配一个缓冲区）
	 * mode: _IOFBF 完全缓冲，缓冲区满时刷新；_IOLBF 行缓冲，缓冲区满时或写入一个换行符时刷新；_IONBF 无缓冲，
	 * size_t: 派生的整数类型，告诉setvbuf()数组的大小
	 * 成功返回0,否则返回非0
	 */
	if(setvbuf(fa,NULL,_IOFBF,BUFSIZE)!=0)
	{
		fputs("Can't create output buffer\n",stderr);
		exit(EXIT_FAILURE);
	}
	puts("Enter name of first source file(empty line to quit):");
	//获取数据来源的文件名，且文件名不为空
	while(s_gets(file_src,SLEN)&&file_src[0]!='\0')
	{
		//比较两个字符串内容是否相同
		if(strcmp(file_src,file_app)==0)
			fputs("Can't append file to itself\n",stderr);
		else if((fs=fopen(file_src,"r"))==NULL)//以读模式打开
			fprintf(stderr,"Can't open %s\n",file_src);
		else
		{
			//设置缓冲区
			if(setvbuf(fs,NULL,_IOFBF,BUFSIZE)!=0)
			{
				fputs("Can't create input buffer\n",stderr);
				continue;
			}
			append(fs,fa);//在目标文件后面 追加源文件内容
			if(ferror(fs)!=0)
				fprintf(stderr,"Error in reading file %s.\n",file_src);
			if(ferror(fa)!=0)
				fprintf(stderr,"Error in writing file %s.\n",file_app);
			fclose(fs);
			files++;
			printf("File %s appended.\n",file_src);
			puts("Next file (empty line to quit):");
		}
	}
	printf("Done appending.%d files appended.\n",files);
	//rewind 让程序回到文件开始处
	rewind(fa);
	printf("%s contents:\n",file_app);
	while((ch=getc(fa))!=EOF)
		putchar(ch);
	puts("Done displaying.");
	fclose(fa);
	return 0;
}

/**
 * fwrite() fread() 用于以二进制形式处理数据
 * 
 * restrict 修饰符只用于指针，该指针是访问数据的唯一且初始方式
 *
 * size_t fwrite(const void * restrict ptr,size_t size, size_t nmemb,FILE * restrict fp)
 * 把二进制数据写入文件，size_of 是标准C类型定义的类型 sizeof运算符返回的类型，通常是unsigned int，但是实现可以选择其他类型
 * ptr: 待写入数据块的地址(第一个参数不是固定的类型，使用时具体类型指针，会被转换成指向void的指针类型，通用类型指针)
 * size：待写入数据块的大小以byte为单位
 * nmemb：表示待写入数据块的数量
 * fp：待写入的文件
 * 返回值为写入项的数量，正常返回nmemb,写入错误返回值小于nmemb
 *
 * size_t fread(void * restrict ptr,size_t size,size_t nmemb,FILE * restrict fp)
 * ptr：待读取文件数据在内存中的地址（读取的文件数据保存地址）
 * size：待读取数据块的大小以byte为单位
 * nmemb：表示待读取数据块的数量 
 * fp：待读取的文件
 * 读取被fwrite写入文件的数据
 * 返回正常情况返回nmemb,读取错误或读取到EOF,该返回值比nmemb小
 */
void append(FILE * source,FILE * dest)
{
	size_t bytes;
	static char temp[BUFSIZE];//只分配一次
	while((bytes=fread(temp,sizeof(char),BUFSIZE,source))>0)
		fwrite(temp,sizeof(char),bytes,dest);
}

char * s_gets(char * st,int n)
{
	char * ret_val;
	char * find;
	ret_val=fgets(st,n,stdin);
	if(ret_val)
	{
		find=strchr(st,'\n');//查找换行符
		if(find)//如果地址不是NULL
			*find='\0';//在此处放置一个空字符
		else
			while(getchar()!='\n')
				continue;
	}
	return ret_val;
}
