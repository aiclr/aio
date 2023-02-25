/**
 * manydice.c 多次掷骰子的模拟程序
 * 与diceroll.c一起编译
 */
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include "1212_diceroll.h"

int main(void)
{
	int dice,roll;
	int sides;
	int status;
	//利用时钟系统初始化随机数种子值
	//ANSI C time()函数返回系统时间，返回类型 time_t 
	//参数为 time_t类型对象的地址，时间值存储在传入的地址上，传入空指针0,只能通过time()函数返回值来提供随机值
	srand((unsigned int)time(0));
	printf("Enter the number of sides per die, 0 to stop.\n");
	while(scanf("%d",&sides)==1 && sides>0)
	{
		printf("How many dice?\n");
		if((status=scanf("%d",&dice))!=1)
		{
			if(status==EOF)
				break;
			else
			{
				printf("You should have entered an integer.");
				printf(" Let's begin again.\n");
				while(getchar()!='\n')
					continue;
				printf("How many sides? Enter 0 to stop.\n");
				continue;
			}
		}
		roll = roll_n_dice(dice,sides);
		printf("You have rolled a %d using %d %d-sided dice.\n",roll,dice,sides);
		printf("How many sides? Enter 0 to stop.\n");
	}
	printf("The rollem() function was called %d times.\n",roll_count);
	printf("GOOD FORTUNE TO YOU!\n");
	return 0;
}
