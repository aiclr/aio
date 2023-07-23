/**
 * 向进程注册遗言函数
 * atexit(3)
 * man 3 atexit(3)
 *     #include <stdlib.h>
 *
 *     int atexit(void (*function)(void));
 * 功能 向进程注册遗言函数function
 * 参数 function 函数指针指向遗言函数的入口地址
 * 返回值 成功 0 错误 非0
 * 遗言函数的注册顺序和调用顺序相反(LIFO Stack结构)
 * 遗言函数注册一次会被调用一次
 * 子进程继承父进程的遗言函数
 * 进程的映像被更换的时候,进程的遗言函数也被覆盖
 *
 *
 * on_exit(3)
 * man 3 on_exit
 */
#include "../stdio_rqk.h"
#include <stdlib.h>
#include <unistd.h>

//进程的遗言函数
void bye(void)
{
	printf("byebye...\n");
	return;
}
void goodbye(void)
{
	printf("goodbye...\n");
	return;
}

int main(void)
{
	//向进程注册遗言函数
	atexit(bye);
	atexit(goodbye);
	
	pid_t pid;
	pid=fork();
	if(pid==-1)E_MSG("fork",-1);
	if(pid==0)
	{
		printf("child process...\n");
	}
	else
	{
		printf("parent process...\n");
	}
	/**
	 * 演示效果不佳 需要输入两次回车终止父子两个进程
	 */
	//getchar();
	
	sleep(2);//父子进程异步都休眠2秒
	return 0;
}
