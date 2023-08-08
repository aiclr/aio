#include "apue.h"

/**
 * man 3 getc
 * man 3 putc
 * man 3 ferror
 * man EOF
 * man stdin
 * man stdout
 */
#include <stdio.h>


/**
 * 与 01_04_cp.c 调用read 和 write程序功能类似
 *
 * gcc 01_05_cp.c apue.c 
 * ./a.out > /tmp/tmp.txt
 * 将用户键入的各行复制到标准输出 '>' 重定向到/tmp/tmp.txt
 * archlinux zsh 换行会结束复制
 * 键入文件结束符 Ctrl+D 终止本次复制
 */
int main(void)
{
	int c;
	while((c=getc(stdin))!=EOF)
	{
		if(putc(c,stdout)==EOF)
			err_sys("output error");
	}
	if(ferror(stdin))
		err_sys("input error");
	exit(0);
}
