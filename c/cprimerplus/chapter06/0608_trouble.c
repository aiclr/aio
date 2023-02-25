/* trouble.c -- 误用= 会导致死循环 */
#include <stdio.h>
int main(void)
{
	long num;
	long sum = 0L;
	int status;
	printf("Please enter an integer to be summed ");
	printf("(q to quit): ");
	status = scanf("%ld", &num);
	// 整个赋值表达式的值就是赋值运算符左侧的值，所以status=1的值也是1
//	while(status = 1) // == 写成 =
	while(status == 1)
	{
		sum = sum + num;
		printf("Please enter next integer (q to quit): ");
		status = scanf("%ld",&num);
	}
	printf("Those integers sum to %ld.\n",sum);
	return 0;
}
