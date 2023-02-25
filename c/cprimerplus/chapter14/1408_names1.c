/**
 * names1.c -- 使用指向结构的指针
 * C 允许把一个结构赋值给另一个结构，但是数组不能
 * 还可以把一个结构初始化为相同类型的另一个结构
 * 	struct names right_field={"Ruthie","George"};
 * 	struct names captain = right_field;
 * 函数不仅能把结构本身作为参数传递,还能把结构作为返回值返回
 * 把结构作为函数参数可以把结构的信息传递给函数，
 * 把结构最为返回值的函数能把结构的信息从被调函数传回主调函数，
 * 结构指针也允许这种双向通信
 *
 * 该例以传递指针的方式处理结构
 *
 */
#include <stdio.h>
#include <string.h>
#define NLEN 30
struct namect
{
	char fname[NLEN];
	char lname[NLEN];
	int letters;
};

void getinfo(struct namect *);
void makeinfo(struct namect *);
void showinfo(const struct namect *);
char * s_gets(char * st,int in);

int main(void)
{
	struct namect person;
	getinfo(&person);
	makeinfo(&person);
	showinfo(&person);
	return 0;
}

void getinfo(struct namect * person)
{
	printf("Please enter your first name.\n");
	s_gets(person -> fname,NLEN);
	printf("Please enter your last name.\n");
	s_gets(person -> lname,NLEN);
}

void makeinfo(struct namect * pst)
{
	pst -> letters = strlen(pst -> fname)+strlen(pst -> lname);
}

void showinfo(const struct namect * pst)
{
	printf("%s %s,your name contains %d letters.\n",pst -> fname,pst -> lname,pst -> letters);
}

char * s_gets(char * st,int in)
{
	char * ret_val;
	char * find;
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
