/* convert.c -- 自动类型转换 */
#include <stdio.h>
int main(void)
{
	char ch; //1byte 8bit
	int i; //4byte 32bit
	float fl; //4byte
	fl = i = ch = 'C'; //赋值 字符 char 'C' ASCII 67
	printf("ch=%c ,i=%d,fl=%.2f\n",ch,i,fl);
	printf("char=%zdbyte,int=%zdbyte,float=%zdbyte\n",sizeof(char),sizeof(int),sizeof(float));
	ch = ch + 1;
	i = fl + 2 * ch;
	fl = 2.0 * ch + i;
	printf("ch=%c ,i=%d,fl=%.2f\n",ch,i,fl);
  ch = 1107; //ch = 1107 % 256 = 83; 'S'
	printf("Now ch = %c\n",ch);
	ch = 80.89; //ch = 80; 'P'
	printf("Now ch = %c\n",ch);

	// cast 强制类型转换
	printf("a = 1.6 + 1.7 =%d\n",(int)(1.6 + 1.7));
	printf("a = (int)1.6 + (int)1.7 =%d\n",(int)1.6+(int)1.7);

	return 0;
}
