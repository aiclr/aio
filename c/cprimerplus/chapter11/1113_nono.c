/* nono.c -- 千万不要模仿 */
#include <stdio.h>
int main(void)
{
	char side_a[]="Side A";
	char dont[] = {'W','O','W','!'};
	char side_b[] ="Side B";
	// 编译器将side_a存储在dont数组后，所以puts 输出到 side_a的'\0' 结束
	// 如果没有side_a 则puts()会一直读取内存中的数据，直到读到'\0' ,无法知道何时结束
	puts(dont);//dont 不是字符串 只是一个字符数组
	return 0;
}
