/* fgets2.c -- 使用fgets() fputs() */
#include <stdio.h>
#define STLEN 10
int main(void)
{
	char words[STLEN];
	puts("Enter strings (empty line to quit):");
	//STLEN = 10 一次读入10-1个字符，存储9个字符加一个末尾的字符串结束标识符'\0' 
	//接着fputs()打印,并不会加多一个换行符，然后words[0]!='\n'进入下一轮循环
	while(fgets(words,STLEN,stdin)!=NULL && words[0]!='\n')
		fputs(words,stdout);
	puts("Done.");
	return 0;
}
