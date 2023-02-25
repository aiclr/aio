/**
 * complit.c -- 复合字面量
 * C99 复合字面量特性可用于结构和数组
 * 如果只需要一个临时结构值，复合字面量很好用
 * 可以使用复合字面量创建一个数组作为函数的参数或赋值给另一个结构
 * 语法是把类型名放在圆括号中，后面紧跟一个用花括号括起来的初始化列表
 */
#include <stdio.h>
#define MAXTITL 41
#define MAXAUTL 31

struct book
{
	char title[MAXTITL];
	char author[MAXAUTL];
	float value;
};

int main(void)
{
	struct book readfirst;
	int score;
	printf("Enter test socre:");
	scanf("%d",&score);
	if(score>=84)
		readfirst=(struct book){"Crime and Punishment","Fyodor Dostoyevsky",11.25};
	else
		readfirst=(struct book){"Mr.Bouncu's Nice Hat","Fred Winsome",5.99};
	printf("Your assigned reading:\n");
	printf("%s by %s:$%.2f\n",readfirst.title,readfirst.author,readfirst.value);
	return 0;
}
