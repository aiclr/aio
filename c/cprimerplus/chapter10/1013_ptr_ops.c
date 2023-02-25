/* ptr_ops.c -- 指针操作 */
#include <stdio.h>
int main(void)
{
	int urn[5] = {100,200,300,400,500};
	int *ptr1,*ptr2,*ptr3;
	ptr1 = urn;//把一个地址赋给指针
	ptr2 = &urn[2];//把index = 2的元素在内存中的起始地址赋给指针
	printf("pointer value, dereferenced pointer,pointer address:\n");
	printf("ptr1=%p,*ptr1=%d,&ptr1=%p\n",ptr1,*ptr1,&ptr1);
	ptr3 = ptr1 + 4;//地址移动4位，index从0 到 4
	printf("ptr1+4=%p,*(ptr1+4)=%d,&urn[4]=%p,ptr3=%p\n",ptr1+4,*(ptr1+4),&urn[3],ptr3);
	ptr1++;
	printf("values after ptr1++:\n");
	printf("ptr1=%p,*ptr1=%d,&ptr1=%p\n",ptr1,*ptr1,&ptr1);
	ptr2--;
	printf("values after ptr2--:\n");
	printf("ptr2=%p,*ptr2=%d,&ptr2=%p\n",ptr2,*ptr2,&ptr2);
	--ptr1;
	++ptr2;
	printf("\nPointers reset to original values:\n");
	printf("ptr1 = %p,ptr2 = %p\n",ptr1,ptr2);
	//一个指针减去另一个指针
	printf("\nsubtracting one pointer from another:\n");
	printf("ptr2= %p,ptr1 = %p,ptr2-ptr1=%td\n",ptr2,ptr1,ptr2-ptr1);
	//一个指针减去一个整数
	printf("\nsubtracting an int from a pointer:\n");
	printf("ptr3 = %p,ptr3-2 = %p\n",ptr3,ptr3-2);
	return 0;
}	
