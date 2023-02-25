/* summing.c -- 根据用户键入的整数求和 */
#include <stdio.h>
int main(void)
{
	long num;
	long sum =0L;
	int status;
	printf("Please enter an integer to be summed (q to quit): ");
  status = scanf("%ld",&num); //当输入的内容与接收的不匹配 scanf 返回0
	while(status == 1)
	{
		sum = sum + num;
		printf("Please enter next integer (q to quit): ");
		status = scanf("%ld",&num);
	}
	printf("Those integers sum to %ld.\n",sum);
	return 0;
}
