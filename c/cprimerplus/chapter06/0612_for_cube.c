/* for_cube.c -- 立方*/
#include <stdio.h>
int main(void)
{
	printf("n    n cubed\n");
	for(int count = 1;count <= 6;count ++)
	{
		printf("%-5d %-5d\n",count,count*count*count);
	}
	return 0;
}
