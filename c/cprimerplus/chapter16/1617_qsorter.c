/**
 * qsort() 快速排序
 * 函数原型
 * void qsort(void *base,size_t nmemb,size_t size,int (*compar)(const void *,const void *));
 * 第一个参数是指针 指向待排序数组的首元素
 * 	ANSI C允许把指向任何数据类型的指针强制转换成指向void的指针
 * 	因此qsort()的第一个实际参数可以引用任何类型的数组
 * 第二个参数是待排序项的数量.
 * 	函数原型把该值转换为 size_t类型.size_t定义在标准头文件中,是sizeof运算符返回的整数类型
 * 由于qsort() 把第一个参数转换为void指针,所以 qsort()不知道数组中每个元素的大小
 * 所以函数原型使用第三个参数显示指明待排序数组中每个元素的大小.
 * 如果排序double类型的数组,第三个参数应该是sizeof(double)
 * 
 * 最后qsort()还需要一个指向函数的指针,这个被指针指向的比较函数,用于确定排序的顺序
 * 该函数应接受两个参数:分别指向待比较两项的指针.
 * 如果第一项的值大于第二项,比较函数则返回正数
 * 如果两项相同则返回0
 * 如果第一项的值小于第二项,则返回负数
 * qsort()根据给定的其他信息计算出两个指针的值,然后把它们传递给比较函数
 */
#include <stdio.h>
#include <stdlib.h>
#define NUM 40

void fillarray(double ar[],int n);
void showarray(const double ar[],int n);
int mycomp(const void *p1,const void *p2);
int main(void)
{
	double vals[NUM];
	fillarray(vals,NUM);
	puts("Random list:");
	showarray(vals,NUM);
	qsort(vals,NUM,sizeof(double),mycomp);
	puts("\nSorted list:");
	showarray(vals,NUM);
	return 0;
}

void fillarray(double ar[],int n)
{
	int index;
	for(index=0;index<n;index++)
		ar[index]=(double)rand()/((double)rand()+0.1);
}

void showarray(const double ar[],int n)
{
	int index;
	for(index=0;index<n;index++)
	{
		printf("%9.4f ",ar[index]);
		if(index%6 == 5)
			putchar('\n');
	}
	if(index % 6 !=0)
		putchar('\n');
}
/* 按从小到打的顺序排序*/
int mycomp(const void *p1,const void *p2)
{
	//要使用指向double的指针来访问这两个值
	const double *a1=(const double *)p1;
	const double *a2=(const double *)p2;
	if(*a1 <*a2)
		return -1;
	else if(*a1==*a2)
		return 0;
	else
		return 1;
}
