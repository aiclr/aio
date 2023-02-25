/* fgets3.c -- 使用fgets() fputs() 处理换行符 */
#include <stdio.h>
#define STLEN 10
int main(void)
{
	char words[STLEN];
	int i;
	puts("Enter strings (empty line to quit):");
	//STLEN = 10 一次读入10-1个字符，存储9个字符加一个末尾的字符串结束标识符'\0' 
	//接着fputs()打印,并不会加多一个换行符，然后words[0]!='\n'进入下一轮循环
	while(fgets(words,STLEN,stdin)!=NULL && words[0]!='\n')
	{
		i=0;
		//先遇到换行符即字符数小于STLEN，if将\n 替换为\0
		//先遇到\0 字符数多于STLEN-1个,else 将多余的字符使用getchar消耗掉
		while(words[i]!='\n' && words[i]!='\0')
			i++;
		if(words[i] =='\n')
			words[i]='\0';
		else
		{
			char c;
			while(c=getchar())//getchar() 从stdio获取一个字符 即超过STLEN的字符
			{
				if(c=='\n')
					break;
				putchar(c);//验证缓冲区多余字符被getchar消耗掉
				continue;
			}
		}
		//输出fgets()获取的字符串
		puts(words);
	}
	puts("Done.");
	return 0;
}
