/**
 * on_exit(3)
 * man 3 on_exit
 * #include <stdlib.h>
 *
 * int on_exit(void (*function)(int, void *), void *arg);
 *
 * 功能 向进程注册遗言函数 function
 * 参数 function 指定遗言函数的入口地址(函数指针)
 *      arg 传递给function函数的唯一参数
 * 返回值 成功 0 错误 非0
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

/**
 * 定义遗言函数
 * int i 是进程 return 的值此例为main函数中return 的 -1
 * 	且(不与0XFF做&运算,exit(3)的return 值会与0XFF做&运算)
 */
void bye(int i,void * ch)
{
	printf("bye...%d...%s\n",i,(char*)ch);
	return;
}

void goodbye(int i,void * ch)
{
	printf("goodbye...%d...%s\n",i,(char*)ch);
	return;
}

int main(void)
{
	//向进程注册遗言函数 FILO
	on_exit(bye,"hello");
	on_exit(goodbye,"hello");
	pid_t pid;
	pid=fork();
	/**
	 * 标准错误处理函数
	 */
	if(pid==-1)
	{
		perror("fork");
		return -1;
	}
#if 0
	// 预处理注释写法
	if(pid==0)
	{
		printf("child process...\n");
	}
	else
	{
		printf("parent process...\n");
        }
#endif
	sleep(2);
	return 0;
}
