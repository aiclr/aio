/**
 * 断言库
 * assert.h 头文件支持的断言库,是一个用于辅助调试程序的小型库
 * 由assert() 宏组成,接受一个整型表达式作为参数
 * 如果表达式求值为假(非零),assert()宏就在标准错误流stderr 中写入一条错误信息
 * 并调用abort()函数终止程序(abort()函数的原型在 stdlib.h头文件中)
 * assert() 宏是为了标识出程序中某些条件为真的关键位置
 * 如果其中的一个具体条件为假,就用 assert() 语句终止程序
 * 通常,assert() 的参数是一个条件表达式或逻辑表达式
 * 如果assert() 中止了程序,它首选会显示失败的测试,包括测试的文件名和行号
 *
 * 使用 assert() 好处
 * 	不仅能自动标识文件和问题所在行号
 * 	还有一种无需更改代码就能开启或关闭 assert() 的机制
 * 如果认为已经排除了程序的bug 可以把下面的宏定义写在包含 assert.h 位置前面
 * 	#define NDEBUG
 * 重新编译程序,这样编译器就会禁用文件中的所有assert() 语句
 * 如果程序又出现问题,可以移除这条指令 #define NDEBUG 
 * 然后重新编译程序,重新启用 assert() 语句
 * */
#include <stdio.h>
#include <math.h>
#include <assert.h>

int main()
{
	double x,y,z;
	puts("Enter a pair of numbers (0 0 to quit): ");
	while(scanf("%lf%lf",&x,&y) == 2 && (x!=0 ||y!=0))
	{
		z=x*x -y*y;//应该用+
	        assert(z >=0);
		printf("answer is %f\n",sqrt(z));
		puts("Next pair of numbers: ");
	}
	puts("Done");
	return 0;
}
