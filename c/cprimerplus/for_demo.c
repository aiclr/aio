/* for_demo.c -- for循环的9种用法 */
#include <stdio.h>
int main(void)
{
	//1. 使用递减运算符来递减计数器
	for(int count =5;count > 0;count--)
	{
		printf("%d seconds!\n",count);
	}
	printf("We have ignition!\n");
	//2. 让计数器递增2,10等
	for(int count = 1;count < 60;count+=10)
	{
		printf("%d\n",count);
	}
	//3. 使用字符char 代替数字计数
	for(char ch = 'a';ch <= 'z';ch++)
	{
		printf("The ASCII value for %c is %d\n",ch,ch);
	}
	//4. 测试其他条件
	for(int count = 1;count*count <=9;count++)
	{
		printf("%d\n",count);
	}
	//5. 让递增的量几何增长
	for(double count = 100.0;count<150.0;count *=1.1)
	{
		printf("Your debt is now $.2f.\n",count);
	}
	//6. for三个表达式可以使用任意合法表达式，无论是什么表达式，每次迭代都会更新该表达式的值
	for(int x=1,y=55;y <= 75;y=(++x*5)+50)
	{
		printf("%10d %10d\n",x,y);
	}
	//7. 可以省略一个或多个表达式，但是不能省略分号，只要在循环中包含能结束循环的语句即可
	for(int n = 3,ans = 2;ans <=25;)
	{
		ans *= n;
	  printf("n = %d;ans = %d.\n",n,ans);
	}
	//8. 第一个表达式不一定是给变量赋值，也可以使用printf() 在执行循环的其他部分之前，只对第一个表达式求值一次或执行一次
	int num = 0;
	for(printf("Keep entering numbers!\n");num !=6;)
	{
		scanf("%d",&num);
	}
	printf("That's the one I want!\n");
	//9. 循环体中的行为可以改变循环头中的表达式
	int delta;
	for(int n = 1;n<1000;n+=delta)
	{
		scanf("%d",&delta);
		printf("n = %d\n",n);
	}
	return 0;
}
