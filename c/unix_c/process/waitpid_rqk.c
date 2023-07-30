/**
 * waitpid(2)
 * man 2 waitpid(2)
 *     #include <sys/wait.h>
 *
 *     pid_t wait(int *_Nullable wstatus);
 *     pid_t waitpid(pid_t pid, int *_Nullable wstatus, int options);
 *
 *     int waitid(idtype_t idtype, id_t id, siginfo_t *infop, int options);
 *
 * pid_t waitpid(pid_t pid, int *_Nullable wstatus, int options);
 * 功能 阻塞等待子进程结束,然后回收子进程的资源
 * 参数 pid 指定要回收的子进程的pid
 *      status 用于储存子进程的退出状态码
 *      options 0 阻塞等待子进程的结束. WNOHANG 非阻塞
 * 返回值 成功 终止子进程的pid.如果options的值为WNOHANG,要回收的子进程还没有结束,返回0
 *        错误 -1 errno做相应的设置
 * 宏的使用等同于wait(2)
 *        The value of pid can be:
 *
 *  < -1   meaning wait for any child process whose process group ID is equal to the abso‐lute value of pid.
 *
 *    -1     meaning wait for any child process.
 *
 *     0      meaning wait for any child process whose process group ID is equal to  that of the calling process at the time of the call to waitpid().
 *
 *   > 0    meaning wait for the child whose process ID is equal to the value of pid.
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
		//父进程阻塞等待回收子进程的资源 
		//waitpid(-1,&s,0);//演示阻塞.效果等同于 wait(&s);
	        int w=waitpid(-1,&s,WNOHANG);//演示非阻塞
	        if(w==0)//没有子进程的终止
		{
			printf("parent exit!\n");
			return 0;
	        }
		if(WIFEXITED(s))//子进程正常终止
			printf("exit code...%d\n",WEXITSTATUS(s));
		if(WIFSIGNALED(s))//子进程被信号打断 比如 kill -2 子进程输出的pid 发送信号2
			printf("signum...%d\n",WTERMSIG(s));
		printf("parent process...\n");
	}
	return 0;
}
