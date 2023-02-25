/* global.c -- 外部变量的工作原理 
 *
 * 外部变量 对定义在其下面的所有函数均可见
 * */
#include <stdio.h>
//外部变量要声明在要使用的函数前面
//外部变量只能初始化一次
//且必须在定义该变量时进行
int units = 0;//定义式声明 defining declaration 会分配内存空间
void critic(void);

int main(void)
{
	//可以省略。 extern 标识外部变量，可以用来指明main()函数使用了units这个外部变量
	extern int units;// referencing declaration 引用式声明 不会引起分配内存空间 
	printf("How many pounds to a firkin of butter?\n");
	scanf("%d",&units);
	while(units !=56)
	{
		critic();
	}
	printf("You must have looked it up!\n");
	return 0;
}

void critic(void)
{
	//不使用extern int units; 也可以直接使用units外部变量
	printf("No luck,my friend. Try again!\n");
	scanf("%d",&units);
}
