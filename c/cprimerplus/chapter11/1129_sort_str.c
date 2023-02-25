/* sort_str.c -- 读入字符串，并排序字符串*/
#include <stdio.h>
#include <string.h>
#include "s_gets.h"

#define SIZE 81 // 限制字符串长度，包括 '\0'
#define LIM 20 // 可读入的最多行数
#define HALT "" //空字符串停止输入
void stsrt(char * strings[],int num);//字符串排序函数
int main(void)
{
	char input[LIM][SIZE];//储存输入的字符串数组
	char *ptstr[LIM];//内含指针变量的数组
	int ct=0;//输入计数
	int k;//输出计数
	printf("Input up to %d lines,and I will sort them.\n",LIM);
	printf("To stop,press the Enter key at a line's start.\n");
	while(ct<LIM && s_gets2(input[ct],SIZE)!=NULL && input[ct][0]!='\0')
	{
		ptstr[ct]=input[ct];
		ct++;
	}
	stsrt(ptstr,ct);
	puts("\nHere's the sorted list\n");
	for(k=0;k<LIM;k++)
		puts(ptstr[k]);
	return 0;
}

void stsrt(char *strings[],int num)
{
	char *temp;
	int top,seek;
	for(top=0;top<num-1;top++)
		for(seek=top+1;seek<num;seek++)
		{	// 借助数轴记忆 第一个参数在前 负数;相同 0;在后，正数; strcmp("A","B") <0  
			if(strcmp(strings[top],strings[seek])>0)//选择排序
			{
				temp=strings[top];
				strings[top]=strings[seek];
				strings[seek]=temp;
			}
		}
}
