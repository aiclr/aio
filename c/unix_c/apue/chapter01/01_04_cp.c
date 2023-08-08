#include "apue.h"

/**
 * man 3 read
 */
#include <unistd.h>

#define BUFFSIZE 4096

/**
 * gcc 01_04_cp.c apue.c
 * 
 * 标准输入是终端,标准输出重定向到文件 /tmp/tmp.txt,标准错误也是中断
 * 如果此输出文件不存在,shell会创建.
 * 该程序将用户键入的各行复制到标准输出,
 * 键入文件结束符 Ctrl+D 将终止本次复制
 * 注意: archlinux zsh 换行会结束复制
 * ./a.out > /tmp/tmp.txt
 * 
 * 将 01_03_ls.c 复制到 /tmp/0103.txt
 * ./a.out < 01_03_ls.c > /tmp/0103.txt
 */
int main(void)
{
  int n;
  char buf[BUFFSIZE];
  while((n=read(STDIN_FILENO,buf,BUFFSIZE))>0)
  {
    if(write(STDOUT_FILENO,buf,n) !=n)
    {
      err_sys("write error");
    }
    if(n<0)
      err_sys("read error");
    exit(0);
  }
}