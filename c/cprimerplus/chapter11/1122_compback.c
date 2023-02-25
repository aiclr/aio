/**
 * compback.c -- strcmp()的返回值 
 * ASCII标准规定
 * 在字母表中第一个字符串在第二个字符串前面 strcmp()返回负数
 *           第一个字符串在第二个字符串后面 strcmp()返回正数
 *           两个字符串相同，strcmp()返回0
 * 如果两个字符串开始的几个字符都相同，strcmp()会依次比较每个字符
 * 直到发现第一对不同的字符为止，然后返回相应的值
 * 如下apples apple s和空字符\0进行比较
 * 空字符在ASCII中排第一，s在其后，所以 strcmp() 返回正数
 * 返回的具体值取决于实现
 */
#include <stdio.h>
#include <string.h>
int main(void)
{
	printf("strcmp(\"A\",\"A\") is %d\n",strcmp("A","A"));
	printf("strcmp(\"A\",\"B\") is %d\n",strcmp("A","B"));
	printf("strcmp(\"B\",\"A\") is %d\n",strcmp("B","A"));
	printf("strcmp(\"C\",\"A\") is %d\n",strcmp("C","A"));
	printf("strcmp(\"Z\",\"a\") is %d\n",strcmp("Z","a"));
	printf("strcmp(\"apples\",\"apple\") is %d\n",strcmp("apples","apple"));
	return 0;
}
