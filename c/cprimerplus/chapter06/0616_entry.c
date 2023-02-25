/* entry.c -- 出口条件循环 */
#include <stdio.h>
int main(void)
{
	const int secret_code = 13;
	int code_entered;
	printf("To enter the triskaidekaphobia therapy club,\n");
  printf("Please enter the secret code number: ");
	scanf("%d",&code_entered);
	while( secret_code != code_entered)
	{
		printf("To enter the triskaidekaphobia therapy club,\n");
		printf("Please enter the secret code number: ");
		scanf("%d",&code_entered);
	}
	printf("Congratulations ! You are cured!\n");
	return 0;
}
