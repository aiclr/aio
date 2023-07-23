/**
 * fork(2)
 * man 2 fork
 *
 *        #include <unistd.h>
 *
 *
 *        pid_t fork(void);
 *        功能 创建一个子进程.当前进程称为父进程,新建的进程称为子进程
 *
 *       返回值(父子两个进程,各有一个返回值)
 *            父进程中 返回-1,代表错误.新建子进程失败
 *                   返回子进程的pid,代表新建子进程成功
 *            子进程中,返回0
 *
 * 子进程一旦创建成功,就是一个独立的调度单位,和父进程是异步的.具体谁先被系统执行,取决于系统的调度系统
 * 子进程和父进程共享父进程的PCB,当有写操作的时候才复制相应的部分
 * 文件描述符和进程的映像都是进程的资源,都记录在进程的PCB中.所以,子进程继承父进程的文件描述符表和进程的映像
 *
 */
#include "../stdio_rqk.h"
#include <unistd.h>

int main(void)
{
    pid_t pid;
    /* 在父进程中创建一个子进程 */
    pid=fork();
    if(pid==-1)E_MSG("fork",-1);
    if(pid==0)/* 子进程执行到的代码 */
    {
        printf("child process...\n");
    }else/* 父进程执行到的代码*/
    {
        printf("parent process...\n");
    }
    //父子进程都能执行到的代码
    return 0;
}
