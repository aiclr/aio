/* loc_stat.c -- 使用局部静态变量
 * static 表示该变量在内存中地址不变，值可以改变
 * 具有文件作用域 file scope 变量也有静态存储期
 * 创建具有静态存储期、块作用域的局部变量，程序离开它所在的函数后，这些变量不会消失
 * 计算机在多次函数调用之间会记录他们的值
 */
#include <stdio.h>
void trystat(void);

int main(void)
{
	int count,a;
	for(count = 1;count<=3;count++)
	{
		printf("Here comes iteration %d:\n",count);
		trystat();
	}
	return 0;
}

void trystat(void)
{
	//每次调用trystat() 都会执行声明语句，运行时行为 runtime
	int fade = 1;
	//静态变量和外部变量在程序被载入内存时已执行完毕，
	//局部静态变量的意义只是为了说明 该变量只有trystat()函数才能看到
	//并未在运行时执行
	static int stay =1;
	printf("fade = %d and stay = %d\n",fade++,stay++);
	printf("fade at %p\n",&fade);
	printf("stay at %p\n",&stay);
}
