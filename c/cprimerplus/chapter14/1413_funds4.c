/**
 * funds4.c -- 把结构数组传递给函数
 * 可以把数组名作为数组中第一个结构的地址传递给函数
 * 可以用数组表示法访问数组中的其他结构
 * 使用数组名传递结构地址的一种间接方法
 */
#include <stdio.h>
#define FUNDLEN 50
#define N 2

struct funds
{
	char bank[FUNDLEN];
	double bankfund;
	char save[FUNDLEN];
	double savefund;
};

double sum(const struct funds money[],int n);
int main(void)
{
	struct funds jones[N]=
	{
		{
		"Garlic-Melon Bank",
		4032.27,
		"Lucky's Saving and Loan",
		8543.94
		},
		{
		"Honest Jack's Bank",
		3620.88,
		"Party Time Savings",
		3802.91
		}
	};
	/**
	 * 数组名jones是该数组的地址，即数组首元素jones[0]的地址，jones和&jones[0]地址相同
	 * money=&jones[0]
	 * money指向jones数组的首元素，所以money[0]是jones数组的另一个名称，money[1]是jones的第二个元素
	 * 每个元素都是一个funds类型的结构
	 * 所以都可以使用运算符(.)来访问funds类型结构的成员
	 */
	printf("The Joneses have a total of $%.2f.\n",sum(jones,N));
	printf("The Joneses have a total of $%.2f.\n",sum(&jones[0],N));
	return 0;
}

double sum(const struct funds money[],int n)
{
	double total;
	int i;
	for(i=0,total=0;i<n;i++)
		total+=money[i].bankfund+money[i].savefund;
	return (total);
}
