/**
 * boolean.c -- 英国数学家 George Boole命名boolean 
 * _Bool类型变量只能存储1真或0假
 * 如果把其他非零数值赋值给_Bool 该值会被设置为1
 * C把所有非零数值都视为真
 */
#include <stdio.h>
int main(void)
{
	long num;
	long sum = 0L;
	_Bool input_is_good;
	printf("Please enter an integer to be summed ");
	printf("(q to quit): ");
	input_is_good = (scanf("%ld", &num) == 1);
	while(input_is_good)
	{
		sum = sum + num;
		printf("Please enter next integer (q to quit): ");
		input_is_good = (scanf("%ld",&num) == 1);
	}
	printf("Those integers sum to %ld.\n",sum);
	return 0;
}
