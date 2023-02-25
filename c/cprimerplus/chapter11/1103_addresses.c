/* addresses.c -- 字符串的地址 */
#define MSG "I'm special"
#include <stdio.h>
int main(void)
{
	//初始化数组把静态存储区的字符串拷贝到数组中，两个内容一致的字符串
	char ar[]=MSG;
	//初始化指针只把字符串的地址拷贝给指针
	const char *pt=MSG;
	printf("address of \"I'm special\": %p \n","I'm special");
	printf("     address ar: %p\n",ar);
	printf("     address pt: %p\n",pt);
	printf("     address MSG: %p\n",MSG);
	printf("address of \"I'm special\": %p \n","I'm special");
	return 0;
}
