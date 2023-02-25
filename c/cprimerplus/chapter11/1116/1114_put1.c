/* put1.c -- 实现puts() 但是不自动添加换行符 */
#include <stdio.h>
#include "myput.h"
void put1(const char *string)
{
	//while(*string!='\0')
	//	putchar(*string++);//++ 优先级高于 *

	//写法2
	//int i;
	//while(string[i]!='\0')
	//	putchar(string[i++]);
	
	//更骚气的写法,string指向空字符时，*string的值是0
	while(*string)
		putchar(*string++);
}
