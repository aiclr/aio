/* getsputs.c -- gets() 和 puts() */
#include <stdio.h>
#define STLEN 5
int main(void)
{
	char words[STLEN];
	puts("Enter a string, please");
	//gets() 唯一参数是words 无法检查数组是否装的下输入行
	//数组名会转换成该数组首元素的地址
	//因此gets()函数只知道数组的开始处，不知道数组中元素个数
	//如果输入的字符串过长，会导致缓冲区溢出buffer overflow
	//多余的字符超出了指定的目标空间
	//如果这些多余的字符只是占用了尚未使用的内存，不会立即出现问题
	//如果它擦写掉程序中的其他数据会导致程序异常中止
	//arch linux 异常提示信息为
	//*** stack smashing detected ***: terminated
	//Aborted (core dumped)
	gets(words);
	printf("Your string twice:\n");
	printf("%s\n",words);
	puts(words);
	puts("Done.");
	return 0;
}
