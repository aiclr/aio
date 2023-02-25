/**
 * names3.c -- 使用指针和malloc()
 * malloc() 和 free() 搭配使用来管理内存
 * malloc()分配内存并使用指针储存该地址
 * 此时在结构中使用指针处理字符串就比较合理
 * 优点
 * 	可以请求malloc()作为字符串分配合适的存储空间
 * 	可以要求用4字节存储“Joe”，用18字节储存“Rasolofomasoandro”
 */
#include <stdio.h>
#include <string.h>//strcpy() strlen()
#include <stdlib.h>//malloc() free()
#define SLEN 81

struct namect
{
	char * fname;
	char * lname;
	int letters;
};

void getinfo(struct namect *);
void makeinfo(struct namect *);
void showinfo(struct namect *);
void cleanup(struct namect *);//释放malloc()分配的内存
char * s_gets(char *,int in);

int main(void)
{
	struct namect person;
	getinfo(&person);
	makeinfo(&person);
	showinfo(&person);
	cleanup(&person);
	return 0;
}

void getinfo(struct namect * pst)
{
	char temp[SLEN];
	printf("Please enter your first name.\n");
	s_gets(temp,SLEN);
        pst->fname=(char *)malloc(strlen(temp)+1);//分配内存给pst的fname
	strcpy(pst->fname,temp);//将字符串拷贝到fname指向的地址上

	printf("Please enter your last name.\n");
	s_gets(temp,SLEN);
	pst->lname=(char*)malloc(strlen(temp)+1);//分配内存给pst的lname
	strcpy(pst->lname,temp);//将字符串拷贝到lname指向的地址上
}

void makeinfo(struct namect * pst)
{
	pst->letters=strlen(pst->fname)+strlen(pst->lname);
}

void showinfo(struct namect * pst)
{
	printf("%s %s.your name contains %d letters.\n",pst->fname,pst->lname,pst->letters);
}

void cleanup(struct namect * pst)
{
	free(pst->fname);
	free(pst->lname);
}

char * s_gets(char * ch,int in)
{
	char * ret_val;
	ret_val=fgets(ch,in,stdin);
	char * find;
	if(ret_val)
	{
		find=strchr(ch,'\n');
		if(find)
			*find='\0';
		else
			while(getchar()!='\n')
				continue;
	}
	return ret_val;
}
