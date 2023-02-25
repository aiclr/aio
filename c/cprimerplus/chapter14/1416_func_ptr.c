/**
 * func_ptr.c -- 使用函数指针
 *
 * 声明一个指向 参数为int,返回值为char 的指针ptr
 * char (*ptr)(int);
 * 通常指向函数的指针---函数指针，常用作另一个函数的参数，告诉该函数要使用哪一个函数。
 * 如C库中的qsort()函数可以处理任意类型的数组，但是要告诉qsort() 使用哪个函数来比较元素。
 * 为此 qsort() 函数的参数列表中，有一个参数接受指向函数的指针。然后qsort()函数使用该函数提供的方案进行排序，无论这个数组中的元素是整数、字符串还是结构。
 *
 * 一个指向int类型变量的指针，该指针储存这个int类型变量在内存中的地址
 * 函数也有地址，函数的机器语言实现由载入内存的代码组成，
 * 函数指针，该指针指向函数代码的起始处地址
 *
 * 声明一个数据指针时，必须声明指针所指向的数据类型。声明一个函数指针时，必须声明指针指向的函数类型。要指明函数的签名（函数的返回类型和形参类型）
 * void ToUpper(char *);//字符转大写的函数
 * void (*pf)(char *);//指向ToUpper函数的指针 pf。把函数名ToUpper替换为表达式(*pf)是创建指向函数指针最简单的方式。
 * 括号很重要
 * void *pf(char *);//pf是参数为字符指针，返回void指针的函数 
 *
 * void 指针是一种特殊的指针，表示为“无类型指针”，
 * 在 ANSI C 中使用它来代替“char*”作为通用指针的类型。
 * 由于 void 指针没有特定的类型，因此它可以指向任何类型的数据。
 * 任何类型的指针都可以直接赋值给 void 指针，而无需进行其他相关的强制类型转换
 *
 * 声明了函数指针后，可以把类型匹配的函数地址赋给它
 * 在这种上下文中，函数名可以用于表示函数的地址
 * void ToUpper(char *);
 * void ToLower(char *);
 * int round(double);
 * void (*pf)(char *);
 * pf=ToUpper;//有效，类型匹配
 * pf=ToLower;//有效，类型匹配
 * pf=round;//无效，类型不匹配
 * pf=ToLwer();//无效，ToLwer()不是地址，且返回类型是void,不能在赋值语句中进行赋值
 * pf指针可以指向其他带char*类型参数、返回类型为void的函数，不能指向其他类型的函数
 *
 * char mis[]="Nina Metier";
 * pf=ToUpper;
 * (*pf)(mis);//语法1：pf指向ToUpper函数，所以*pf=ToUpper函数，所以(*pf)(mis)和ToUpper(mis)相同，从ToUpper函数和pf的声明可以看出，ToUpper和(*pf)等价
 * pf=ToLower;
 * pf(mis);//语法2：函数名是指针，所以指针和函数名可以互换使用，所以pf(mis)和ToLower(mis)相同，从pf赋值表达式语句能看出ToLower和pf是等价的
 * 由于历史原因，贝尔实验室的C和UNIX的开发者采用第一种形式，而伯克利的UNIX推广者却采用第二种形式，K&R C不允许第二种形式。
 * 但是为了与现有代码兼容，ANSI C认为两种形式等价，后续标准延续了这种矛盾的和谐
 *
 */
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#define LEN 81
//优化写法
typedef void(*V_FP_CHARP)(char*);

char *s_gets(char *,int);
char showmenu(void);
void eatline(void);//读取至行末尾
void show(void (*fp)(char*),char *);
//void show(V_FP_CHARP fp,char*);
void ToUpper(char*);//把字符串转换为大写
void ToLower(char*);//把字符串转换为小写
void Transpose(char*);//大小写转置
void Dummy(char*);//不更改字符串

int main(void)
{
	char line[LEN];
	char copy[LEN];
	char choice;
	//声明一个函数指针，被指针指向的函数 参数为char* 返回值void
	void (*pfun)(char*);
	//V_FP_CHARP pfun;
	puts("Enter a string (empty line to quit):");
	while(s_gets(line,LEN)!=NULL && line[0]!='\0')
	{
		while((choice=showmenu())!='n')
		{
			switch(choice)
			{
				case 'u':pfun=ToUpper;break;
				case 'l':pfun=ToLower;break;
				case 't':pfun=Transpose;break;
				case 'o':pfun=Dummy;break;
			}
			strcpy(copy,line);//为show()函数拷贝一份
			show(pfun,copy);
		}
		puts("Enter a string(empty line to quit):");
	}
	puts("Bye!");
	return 0;
}
char showmenu(void)
{
	char ans;
	puts("Enter menu chioce:");
	puts("u)uppercase	l)lowercase");
	puts("t)transposed case o)original case");
	puts("n)next string");
	ans=getchar();
	ans=tolower(ans);
	eatline();
	while(strchr("ulton",ans)==NULL)
	{
		puts("Please enter a u,l,t,o,or n:");
		ans=tolower(getchar());
		eatline();
	}
	return ans;
}
void eatline(void)
{
	while(getchar()!='\n')
		continue;
}
void ToUpper(char* str)
{
	while(*str)
	{
		*str=toupper(*str);
		str++;
	}
}
void ToLower(char* str)
{
	while(*str)
	{
		*str=tolower(*str);
		str++;
	}
}
void Transpose(char* str)
{
	while(*str)
	{
		if(islower(*str))
			*str=toupper(*str);
		else if(isupper(*str))
			*str=tolower(*str);
		str++;
	}
}

void Dummy(char * str)
{
	//不改变字符串
}
void show(void (*fp)(char *),char *str)
{
	(*fp)(str);
	puts(str);
}
char *s_gets(char* ch,int in)
{
	char * ret_val;
	char * find;
	ret_val=fgets(ch,in,stdin);
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




