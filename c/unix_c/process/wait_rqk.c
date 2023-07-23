/**
 * wait(2)
 * man 2 wait
 * 
 * pid_t wait(int *_Nullable wstatus);
 * pid_t waitpid(pid_t pid, int *_Nullable wstatus, int options);
 * int waitid(idtype_t idtype, id_t id, siginfo_t *infop, int options);
 *
 */
#include "../stdio_rqk.h"
#include <sys/wait.h>
#include <unistd.h>
#include <stdlib.h>

int main(void)
{
	int s;
	//创建子进程
	pid_t pid=fork();
	if(pid==-1)E_MSG("fork",-1);//自定义统一异常处理宏
	if(pid==0)//子进程执行代码
	{
		printf("child process...%d\n",getpid());
		getchar();
		exit(-1);//exit(2) return值会与0xff & 运算
	}
	else//父进程执行代码
	{
		wait(&s);//父进程阻塞等待回收子进程的资源
		if(WIFEXITED(s))//子进程正常终止
			printf("exit code...%d\n",WEXITSTATUS(s));
		if(WIFSIGNALED(s))//子进程被信号打断 比如 kill -2 子进程输出的pid 发送信号2
			printf("signum...%d\n",WTERMSIG(s));
		printf("parent process...\n");
	}
	return 0;
}
