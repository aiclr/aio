/* forc99.c -- 新的C99块规则
 * 循环或if的一部分即使不用花括号，也是一个block
 * 整个循环是它所在块的子块 sub-block
 * 循环体是整个循环块的子块
 * if类似
 * */
#include <stdio.h>
int main()
{
	int n=8;
	printf(" Initially, n = %d at %p\n",n,&n);
	for(int n=1;n<3;n++)
		printf("     loop 1:n=%d at %p\n",n,&n);
	printf("After loop 1,n=%d at %p\n",n,&n);
	for(int n=1;n<3;n++)//循环体中的n 隐藏了外部的n
	{
		printf(" loop 2 index n=%d at %p\n",n,&n);
		int n=6;//新声明的n 隐藏了循环体中声明的n
		printf(" loop 2:n =%d at %p\n",n,&n);//n=6的值和地址，结束一轮循环后，int n=6;生命结束，循环体中n继续起作用
		n++;//int n=6; ++，不是循环体的n++；
	}
	printf("After loop 2,n=%d at %p\n",n,&n);
	return 0;
}
