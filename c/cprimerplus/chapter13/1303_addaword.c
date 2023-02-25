/* addaword.c -- 使用fprintf() fscanf() rewind()
 * fscanf() 和scanf() 无法读取空格和换行符
 * fscanf() 返回值 是读取到的个数，fscanf(stdin,"%40s",words)==1 读取到1个字符串
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX 41 //每行最大字符数+1

int main(void)
{
	FILE *fp;
	char words[MAX];
	if((fp=fopen("wordy","a+"))==NULL)
	{
		fprintf(stderr,"Can't open \"wordy\" file.\n");
		exit(EXIT_FAILURE);
	}
	puts("Enter words to add to the file;press the #");
        puts("key at the begining of a line to terminate.");
        //一行最多40个字符，空格 换行符 不会写入文件
	while((fscanf(stdin,"%40s",words)==1)&&(words[0]!='#'))
		fprintf(fp,"%s\n",words);
	puts("File contents:");
	rewind(fp);//返回到文件开始处,输出文件内容
	while(fscanf(fp,"%s",words)==1)
		puts(words);
	puts("Done!");
	if(fclose(fp)!=0)
		fprintf(stderr,"Error closing file\n");
	return 0;
}
