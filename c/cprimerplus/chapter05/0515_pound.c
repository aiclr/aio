/* pound.c -- 定义一个带一个参数的函数 */
#include <stdio.h>
void pound(int n);//ANSI函数原型声明
int main(void)
{
	int times = 5;
	char ch = '!'; //ASCII码=33
	float f = 6.0f;
	pound(times);//int 类型参数
	pound(ch);// 和pound((int)ch);相同 cast 强制转换
	pound(f);//float cast to int 强制转换
	return 0;
}

void pound(int n)
{
	while(n-- > 0)
		printf("#");
	printf("\n");
}
