/**
 * man 3 strerror
 */
#include <string.h>
/**
 * man 3 errno
 * EACCES
 * ENOENT
 */
#include <errno.h>

/**
 * man 3 fprintf
 * man 3 stderr
 * man 3 perror
 */
#include <stdio.h>

/**
 * man 3 exit
 */
#include <stdlib.h>


int main(int argc,char *argv[])
{
	/**
	 * char *strerror(int errnum); 函数将errnum(通常是errno值)映射为一个出错消息字符串,并且返回此字符串的指针
	 */
	fprintf(stderr,"EACCES: %s\n",strerror(EACCES));

	errno=ENOENT;
	/**
	 * void perror(const char *s); 基于 errno的当前值,在标准错误上产生一条出错消息,然后返回.
	 * 首先输出由s指向的字符串,然后一个冒号,一个空格,errno值对应的出错消息,最后一个换行符
	 *
	 * argv[0] 值为./a.out,作为参数传递给perror.这是一个标准的UNIX惯例.
	 * 使用此种惯例,在程序作为管道的一部分执行时,能分清是哪个程序产生出错消息
	 */
	perror(argv[0]);
	exit(0);
}
