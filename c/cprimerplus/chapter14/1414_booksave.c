/**
 * booksave.c -- 在文件中保存结构中的内容
 * 结构可以储存不同类型的信息，所以它是构建数据库的重要工具
 * 该例是把结构写入文件和检索他们的最简单方法，但是这种方法浪费存储空间，
 * 保存了结构中未使用的部分
 * 结构大小是2 x 40 x sizeof(char) + sizeof(float) 共 84 byte
 * 实际上不是每个输入项都需要这么多空间
 * 但是 让每个输入块大小相同在检索数据时很方便
 *
 * 另一个方法是使用可变大小的记录，为了方便读取文件中的这种记录，每个记录以数值字段规定记录的大小
 * 这比上一种方法复杂，通常这种方法涉及链式结构和动态内存分配
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAXTITL 40
#define MAXAUTL 40
#define MAXBKS 10//最大书籍数量

char * s_gets(char *,int);

struct book
{
	char title[MAXTITL];
	char author[MAXAUTL];
	float value;
};
int main(void)
{
	//结构数组
	struct book library[MAXBKS];
	int count=0;
	int index,filecount;
	FILE *pbooks;
	int size =sizeof(struct book);
	//a+b a写模式打开文件 在末尾添加，不存在则新建，b二进制文件格式
	//fread() fwrite() 使用二进制文件
	if((pbooks=fopen("book.dat","a+b"))==NULL)
	{
		fputs("Can't open book.dat file\n",stderr);
		exit(1);
	}
	//定位到文件开始
	rewind(pbooks);
	while(count<MAXBKS && fread(&library[count],size,1,pbooks)==1)
	{
		if(count==0)
			puts("Current contents of book.dat:");
		printf("%s by %s:$%.2f\n",library[count].title,library[count].author,library[count].value);
		count++;
	}
	filecount=count;
	if(count==MAXBKS)
	{
		fputs("The book.dat file is full.",stderr);
		exit(2);
	}
	puts("Please add new book titles.");
	puts("Please [enter] at the start of a line to stop.");
	while(count<MAXBKS && s_gets(library[count].title,MAXTITL)!=NULL && library[count].title[0]!='\0')
	{
		puts("Now enter the author.");
		s_gets(library[count].author,MAXAUTL);
		puts("Now enter the value.");
		scanf("%f",&library[count++].value);
		while(getchar()!='\n')
			continue;
		if(count < MAXBKS)
			puts("Enter the next title.");
	}
	if(count>0)
	{
		puts("Here is the list of your books:");
		for(index=0;index<count;index++)
			printf("%s by %s:$%.2f\n",library[index].title,library[index].author,library[index].value);
		//把结构大小的块写入文件，count-filecount=新添加的书籍数量（写入文件次数）
		fwrite(&library[filecount],size,count-filecount,pbooks);
	}
	else
		puts("No books?Too bad.\n");
	puts("Bye.\n");
	fclose(pbooks);
	return 0;
}

char *s_gets(char *st,int in)
{
	char *ret_val;
	char *find;
	ret_val=fgets(st,in,stdin);
	if(ret_val)
	{
		find=strchr(st,'\n');
		if(find)
			*find='\0';
		else
			while(getchar()!='\n')
				continue;
	}
	return ret_val;
}
