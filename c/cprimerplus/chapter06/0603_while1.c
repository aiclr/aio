/* while1.c -- 名面上是死循环 但是达到int 最大值时再+1变成负数最小值，这样看其实不是死循环 */
#include <stdio.h>
int main(void)
{
	int n = 0;
	while(n < 3)
		printf("n is %d\n",n);
		n++;
	printf("That's all this program does\n");
	return 0;
}
