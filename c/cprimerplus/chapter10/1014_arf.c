/* arf.c -- 处理数组的函数 */
#include <stdio.h>
#define SIZE 5
//const 限制修改数组
void show_array(const double [],int n);
void mult_array(double [],int,double);

int main(void)
{
	double dip[SIZE] = {20.0,17.66,8.2,15.3,22.22};
	printf("The original dip array:\n");
	show_array(dip,SIZE);
	mult_array(dip,SIZE,2.5);
	show_array(dip,SIZE);
	return 0;
}

void show_array(const double ar[],int n)
{
	for(int index =0;index<n;index++)
		printf("%8.3f ",ar[index]);
	putchar('\n');
}

void mult_array(double ar[],int n,double mult)
{
	for(int index =0;index<n;index++)
		ar[index]*=mult;
}
