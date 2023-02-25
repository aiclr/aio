/**
 * fgets1.c -- 使用fgets() 和 fputs() 
 * fgets() 专门处理文件输入的函数，通常与fputs()配对使用
 * 第二个参数指明读入字符的最大数量，解决gets() buffer overflow 溢出的问题
 * 第三个参数指明要读入的文件，如果读入键盘输入的数据，以stdin作为参数
 * fgets() 读到一个换行符，会存储到字符串中，而gets()会丢弃换行符
 *
 * fputs() 
 * 第二个参数指明要写入的文件，如果显示在显示器上，应该使用stdout最为参数
 * puts()函数会在待输出字符串的末尾添加一个换行符，而fputs()不会
 *
 * fputs()函数返回指向char的指针
 * 如果一切正常该函数返回的地址与传入的第一个参数相同
 * 如果函数读到文件末尾 EOF 将返回一个特殊的指针:空指针 null pointer
 *
 * null pointer 不会指向有效数据，可用于表示读到文件末尾这一特殊情况
 * 在代码中可以使用0代替，不过在c语言中用宏NULL来代替更常见
 * 如果在读入数据时出现某些错误，也返回NULL
 *
 * */
#include <stdio.h>
#define STLEN 14
int main(void)
{
	char words[STLEN];//分配内存
	puts("Enter a string, please.");
	fgets(words,STLEN,stdin);
	printf("Your string twice(puts(),then fputs()):\n");
	puts(words);
	fputs(words,stdout);
	puts("Enter another string, please");
	fgets(words,STLEN,stdin);
	printf("Your string twice(puts(),then fputs()):\n");
	puts(words);
	fputs(words,stdout);
	puts("Enter another string, please");
	return 0;
}
