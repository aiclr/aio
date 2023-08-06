#include "apue.h"
#include <sys/wait.h>

/**
 * man 2 fork
 * man 3 execlp
 */
#include <unistd.h>

int main(void)
{
	char buf[MAXLINE]; /* from apue.h */
	pid_t pid;
	int status;
	printf("%% ");/* print prompt (printf requires %% to print %) */
	/**
	 * 标准I/O函数fgets 从标准输入一次读取一行
	 * 当键入文件结束符 Ctrl + D作为第一个字符时,fgets返回一个NULL指针,循环终止,进程终止 
	 */
	while(fgets(buf,MAXLINE,stdin)!=NULL){
		/**
		 * 因为fgets返回的每一行都以换行符终止,后随一个null字节
		 * 因此使用标准C函数strlen,计算此字符串的长度,然后用一个null字节替换换行符
		 * 因为execlp函数要求参数是以null结束而不是以换行符结束
		 *
		 */
		if(buf[strlen(buf)-1]=='\n')
			buf[strlen(buf)-1]=0;/* replace newline with null */
		/**
		 * 调用fork 创建一个新进程,新进程(子进程)是调用进程(父进程)的一个副本
		 * fork 对父进程返回子进程的进程id,对子进程则返回0
		 * fork调用一次,返回两次,父子进程各返回一次
		 */
		if((pid=fork())<0)
			err_sys("fork error");
		else if(pid==0){/* child */
			/**
			 * 子进程中,调用execlp执行从标准输入读入的命令
			 * 使用新的程序文件替换子进程原先执行的程序文件
			 */
			execlp(buf,buf,(char*)0);
			err_ret("couldn't execute: %s",buf);
			exit(127);
		}
		/* parent */
		/**
		 * 子进程调用execlp执行新程序文件
		 * 父进程通过waitpid等待子进程终止
		 * waitpid 返回子进程终止状态status变量,此值可用于准确判定子进程是如何终止
		 */
		if((pid=waitpid(pid,&status,0))<0)
			err_sys("waitpid error");
		printf("%%");
	}
	exit(0);
}

