/**
 * flexmemb.c -- 伸缩型数组成员 c99新增特性
 * C99新增特性 伸缩型数组成员 flexible array member
 * 利用此特性声明结构，结构的最后一个数组成员具有一些特性
 * 1. 该数组不会立即存在
 * 2. 使用伸缩型数组成员可以编写合适的代码，使其具有所需数目的元素
 * 声明一个伸缩型数组成员规则
 * 1. 伸缩型数组成员必须是结构的最后一个成员
 * 2. 结构中必须至少有一个成员
 * 3. 伸缩型数组的声明类似于普通数组，只是它的方括号内是空的，未指定大小
 *
 * 注意
 * 1. 不能使用结构进行赋值或拷贝
 * 	不能*pf2=*pf1,这样只能拷贝除伸缩型数组成员以外的其他成员，拷贝使用memcpy()
 * 2. 不能以按值方式把这种结构传递给结构，按值传递一个参数与赋值类型，要把结构的地址传递给函数
 * 3. 不要使用带伸缩型数组成员的结构作为数组成员或另一个结构的成员
 *
 * 这种类似于在结构中最后一个成员是伸缩型数组的情况称为：struct hack
 * 除了伸缩型数组成员在声明时用空的方括号外，struct hack特指大小为0的数组
 * struct hack是针对特殊编译器GCC,不属于C标准
 * 这种伸缩型数组成员方法是标准认可的编程技巧 
 */
#include <stdio.h>
#include <stdlib.h>

struct flex
{
	size_t count;
	double average;
	/**
	 * 声明此结构变量时，不能用scores做任何事
	 * 因为没有给这个伸缩型数组预留存储空间
	 * 所以声明此结构时，声明指向该结构类型的指针，然后使用malloc()和free()来管理该结构所占用内存
	 * 从而达到伸缩数组的效果
	 */
	double scores[];
};

void showFlex(const struct flex *p);

int main(void)
{
	struct flex *pf1,*pf2;
	int n=5;
	int i;
	int tot=0;
	//为结构和伸缩数组分配内存
	pf1=malloc(sizeof(struct flex)+n*sizeof(double));
	//使用指针访问结构成员
	pf1->count=n;
	for(i=0;i<n;i++)
	{
		pf1->scores[i]=20.0-i;
		tot+=pf1->scores[i];
	}
	pf1->average=tot/n;
	showFlex(pf1);
	n=9;
	tot=0;
	pf2=malloc(sizeof(struct flex)+n*sizeof(double));
	pf2->count=n;
	for(i=0;i<n;i++)
	{
		pf2->scores[i]=20.0-i/2.0;
		tot+=pf2->scores[i];
	}
	pf2->average=tot/n;
	showFlex(pf2);
	free(pf1);
	free(pf2);
	return 0;
}

void showFlex(const struct flex *p)
{
	int i;
	printf("Score:");
	for(i=0;i<p->count;i++)
		printf("%g ",p->scores[i]);
	printf("\nAverage:%g\n",p->average);
}
