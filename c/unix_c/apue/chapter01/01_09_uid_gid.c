/**
 * man 3 printf
 */
#include <stdio.h>
/**
 * man 3 getuid
 * man 3 getgid
 */
#include <unistd.h>
/**
 * man 3 exit
 */
#include <stdlib.h>

/**
 * cat /etc/group
 */
int main(void)
{
	printf("uid = %d,gid = %d\n",getuid(),getgid());
	exit(0);
}
