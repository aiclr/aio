/**
 * while2.c 
 * while语句是符合语句
 * 语句构成上，它是一条单独的语句
 * 该语句从while 开始执行，到第一个分号结束
 * 在使用了复合语句的情况下，到右花括号结束
 */
#include <stdio.h>
int main(void)
{
	int n = 0;
	while(n++ < 3)
		printf("n is %d\n",n);
	int m = 0;
	//单独语句到分号结束
	while(m++ < 3);
		printf("m is %d\n",m);
	int p = 0;
	//复合语句，到右花括号结束
	while(p++ < 3){}
	printf("p is %d\n",p);
	// 跳过输入到第一个非空白字符或数字
	int q = 0;
	while(scanf("%d",&q) == 1)
		;
	printf("input q is %d\n",q);
	printf("That's all this program does.\n");
	return 0;
}
