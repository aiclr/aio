/* strptr.c -- 把字符串看作指针 */
#include <stdio.h>
int main(void)
{
	//*"space farers"  字符串是一个char[]数组，"xxx" 代表字符串所指向的地址，解引用得该地址上的值，即第一个元素 字符's' 
	printf("%s,%p,%c\n","We","are",*"space farers");
	return 0;
}
