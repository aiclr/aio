/**
 * atexit() 示例
 * atexit()函数使用函数指针,退出时将要调用的函数地址传递给 atexit() .
 * 函数名作为函数参数时相当于该函数的地址
 * 首先 atexit()注册函数列表中的函数,当调用exit() 时就会执行这些被注册的函数
 * 
 * ANSI 保证 在这个列表中至少可以放32个函数
 * 最后调用exit()函数时,exit()会执行这些函数
 * (执行顺序与列表中的函数顺序相反(栈结构后进先出),即最后添加的函数最先执行)
 * 
 * atexit() 注册的函数应该不带任何参数且返回类型为void
 * 通常这些函数会执行一些清理任务,例如更新监视程序的文件或重置环境变量
 * 注意即使没有显示调用 exit() .还是会调用注册的函数,因为main() 结束时会隐式调用 exit()
 *
 *
 * exit() 函数用法
 * exit() 执行玩atexit()指定的函数后,会完成一些清理工作:
 * 	刷新所有输出流
 * 	关闭所有打开的流
 * 	关闭由标准I/O函数tmpfile()创建的临时文件
 * 然后exit() 把控制权返回主机环境,如果可能的话,向主机环境报告终止状态.
 * 通常UNIX程序使用0表示成功终止,非零表示终止失败
 * UNIX返回的代码并不适用于所有的系统,
 * 所以ANSI C为了可移植性的要求,定义了一个名为 EXIT_FAILURE 的宏表示终止失败.
 * ANSI C 还定义了EXIT_SUCCESS 表示成功终止.
 * 不过exit()函数也接受0表示成功终止
 * 
 * 在ANSI C中,在非递归的 main()中使用 exit() 函数等价于使用关键字return
 * 
 * 在main() 以外的函数中使用exit()也会终止整个程序
 **/
#include <stdio.h>
#include <stdlib.h>
void sign_off(void);
void too_bad(void);

int main(void)
{
	int n;
	atexit(sign_off);//注册 sign_off() 函数
        puts("Enter an integer:");
	if(scanf("%d",&n)!=1)
	{
		puts("That's no integer!");
		atexit(too_bad);//注册 too_bad() 函数
		exit(EXIT_FAILURE);
	}
	printf("%d is %s.\n",n,(n%2==0)?"even":"odd");
	return 0;
}

void sign_off(void)
{
	puts("Thus terminates another magnificent program from");
	puts("SeeSaw Software!");
}

void too_bad(void)
{
	puts("SeeSaw Software extends its heartfelt condolences");
	puts("to you upon the failure of your program.");
}
