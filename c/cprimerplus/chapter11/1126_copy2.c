/* copy2.c -- 使用strcpy() */
#include <stdio.h>
#include <string.h>
#define WORDS "beast"
#define SIZE 40
int main(void)
{
	//char[] orig={'b','e','a','s','t','\0'}
	const char *orig = WORDS;
	char copy[SIZE]= "Be the best that you can be.";
	char *ps;
	puts(orig);
	puts(copy);
	//从index=7处开始拷贝，会把\0 也拷贝在内
	ps = strcpy(copy+7,orig);
	//char copy[SIZE]= "Be the beast'\0'hat you can be.";
	puts(copy);//输出到\0
	puts(ps);//起始地址为copy[7]
	return 0;
}
