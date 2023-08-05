#include "apue.h"

/**
 * man 3 getpid
 */
#include <unistd.h>

/**
 * gcc 01_06_getpid.c
 * ./a.out
 */
int main(void)
{
	printf("hello world from process ID %ld\n",(long)getpid());
	exit(0);
}
