/**
 * string.h 库中的 memcpy() 和memmove()
 * 不能把一个数组赋给另一个数组,所以要通过循环把数组中的每个元素赋给另一个数组相应的元素
 * 例外情况是: strcpy() 和 strncpy() 函数来处理字符数组
 * memcpy() 和memmove() 函数提供类似的方法处理任意类型的数组
 * 函数原型 
 * 	void *memcpy(void *restrict s1,const void *restrict s2,size_t n);
 * 	void *memmove(void *s1,const void *s2,size_t n);
 * 函数从s2 指向的位置拷贝n字节到s1 指向的位置,而且都返回s1的值
 * 不同的是
 * 	memcpy()的参数带关键字 restrict 即 memcpy() 假设两个内存区域之间没有重叠
 * 	memmove()不作假设,所以拷贝过程类似于先把所有字节拷贝到一个临时缓冲区,然后再拷贝到最终目的地
 * 如果使用memcpy() 时,两区域出现重叠的行为是未定义的,这意味着该函数可能正工作,也可能失败.
 * 编译器不会在本不该使用memcpy()时禁止使用,作为程序员,在使用该函数时有责任确保两个区域不重叠
 *
 * 由于这两个函数设计用于处理任何数据类型,所以它们的参数都是两个指向 void 的指针
 * C允许把任何类型的指针赋给void *类型的指针.导致函数无法知道待拷贝数据的类型.
 * 因此这两个函数使用第三个参数指明待拷贝的字节数
 * 对数组而言,字节数一般与元素个数不同,如果要拷贝数组中10个double类型的元素,要使用10*sizeof(double),而不是10
 *
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define SIZE 10

void show_array(const int ar[],int n);

//如果编译器不支持C11的_Static_assert 可以注释掉下面这行
_Static_assert(sizeof(double) == 2*sizeof(int),"double not twice int size");
int main(void)
{
	int values[SIZE] = {1,2,3,4,5,6,7,8,9,10};
	int target[SIZE];
	double curious[SIZE/2] = {2.0,2.0e5,2.0e10,2.0e20,5.0e30};
	puts("memcpu() used:");
	puts("values (original data): ");
	show_array(values,SIZE);
	memcpy(target,values,SIZE * sizeof(int));
	puts("target (copy of values):");
	show_array(target,SIZE);
	puts("\nUsing memmove() with overlapping ranges:");
	memmove(values +2,values,5*sizeof(int));
	puts("values -- elements 0-4 copied to 2-6:");
	show_array(values,SIZE);
	puts("\nUsing memcpy() to copy double to int:");
	// memcpy() 从double类型数组中把数据拷贝到int类型数组中
	// 表明memcpy()函数不知道也不关心数据类型,只负责从一个位置把一些字节拷贝到另一个位置
	// 例如从结构中拷贝数据到字符数组中
	// 拷贝过程也不会进行数据转换
	// 如果用循环对数组中的每个元素赋值,double类型的值会在赋值过程被转换为int类型的值
	// 这种情况下,按原样拷贝字节,然后程序把这些位组合解释成int类型
	memcpy(target,curious,(SIZE/2)*sizeof(double));
	puts("target -- 5 doubles into 10 int positions:");
	show_array(target,SIZE/2);
	show_array(target+5,SIZE/2);
	return 0;
}

void show_array(const int ar[],int n)
{
	int i;
	for(i=0;i<n;i++)
		printf("%d ",ar[i]);
	putchar('\n');
}
