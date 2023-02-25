/* dyn_arr.c -- malloc() free() exit() EXIT_FAILURE 都定义在 stdlib.h 
 * malloc() 分配内存
 * 	参数: 所需的内存字节数 可以使用 2*sizeof(int)计算字节数
 * 	返回: 寻找合适大小的空闲内存块,此时这部分内存是匿名的,然后返回内存块的首字节地址
 * 	失败: 可能没有合适大小的空闲内存块，无法分配内存，将返回空指针，此时可以调用exit() 结束程序
 * calloc() 分配内存
 *      参数: 两个无符号整数 unsigned int ; 
 *            第一个参数是所需的存储单元数量
 *            第二个参数是存储单元的大小(单位字节)
 *      返回: 同malloc()
 * free() 释放 malloc() 或 calloc() 分配的内存
 * 	参数: malloc() 或 calloc() 返回的内存块首字节地址
 * exit()结束程序，EXIT_FAILURE 或 0 表示程序结束
 *
 * 声明动态数组（运行时确定数组大小）
 */
#include <stdlib.h>
#include <stdio.h>

int main(void)
{
	double* ptd;
	int max;
	int number;
	int i=0;
	puts("What is the maximum mumber of type double entries?");
	if(scanf("%d",&max)!=1)
	{
		puts("Number not correctly entered -- bye.");
		exit(EXIT_FAILURE);
	}
	//ptd = (double*) malloc(max * sizeof(double));
	ptd = (double*) calloc(max,sizeof(double));
	if(ptd==NULL)
	{
		puts("Memory allocation failed.Goodbye.");
		exit(0);
	}
	puts("Enter the values (q to quit):");
	while(i<max && scanf("%lf",&ptd[i])==1)
		i++;
	printf("Here are your %d entries:\n",number = i);
	for(i=0;i<max;i++)
	{
		printf("%7.2f ",ptd[i]);
		if(i%7==6)
			putchar('\n');
	}
	if(i%7!=0)
		putchar('\n');
	puts("Done!");
	free(ptd);
	return 0;
}
