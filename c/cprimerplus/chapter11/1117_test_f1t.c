/* test_f1t.c -- 使用缩短字符串长度的函数 */
#include <stdio.h>
#include <string.h>
void f1t(char *,unsigned int);
int main(void)
{
	char mesg[]="Things should be as simple as possible, but not simpler.";
	puts(mesg);
	f1t(mesg,38);//把逗号 换成了'\0'
	puts(mesg);
	puts("Let's look at some more of the string.");
	puts(mesg+39);
	return 0;
}

void f1t(char * string,unsigned int size)
{
	if(strlen(string)>size)
		string[size]='\0';
}
