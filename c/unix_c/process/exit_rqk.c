/**
 * exit(3)
 * man 3 exit
 *
 *        #include <stdlib.h>
 *
 *        [[noreturn]] void exit(int status);
 *        代表进程的正常终止,在程序的任何地方使用都会终止当前进程
 *        功能 使进程正常终止
 *        参数
 *            status 将 status & 0xFF 的值返回给父进程,父进程调用 wait(2) 来回收
 *
 * wait(2)
 * man 2 wait
 */
#include <stdlib.h>
#include <stdio.h>

int main(void)
{
	getchar();//等待输入防止进程结束
	//exit(1); //echo $? >_ 1
	//exit(-1);  // echo $? >_ 255
	//exit(255); //echo $? >_ 255
	//exit(256); //echo $? >_ 0
	exit(257); //echo $? >_ 1
}
