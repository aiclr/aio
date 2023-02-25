/* zeno.c 求序列的和 1 + 1/2 + 1/4 + 1/8 +1/16 +... + 1/2^n-1 
 * 希腊哲学家Zeno 提出箭永远不会达到它的目标
 * 他认为箭要达到目标距离的一半
 * 然后再达到剩余距离的一半，依次类推，无穷无尽
 * 数学方法证明：
 * S = 1 + 1/2 + 1/4 +...
 * S/2 =1/2 + 1/4 +...
 * S - S/2 = 1 = S/2 => S = 2
 */
#include <stdio.h>
int main(void)
{
	int t_ct;//项计数
	double time,power_of_2;
	int limit;
	printf("Please enter the number of terms you want: ");
	scanf("%d",&limit);
	for(time = 0,power_of_2 = 1,t_ct = 1; t_ct <= limit; t_ct++,power_of_2*=2.0)
	{
		time += 1.0/power_of_2;
		printf("time = %f when terms = %d.\n",time,t_ct);
	}
	return 0;
}
