/* rows2.c -- 依赖外部循环的嵌套循环 */
#include <stdio.h>
#define ROWS 6
#define CHARS 10
int main(void)
{
	for(int row = 0;row < ROWS;row++)
	{
		for(char ch ='A'+row;ch < 'A'+CHARS;ch++)
			printf("%c",ch);
		printf("\n");
	}
	return 0;
}
