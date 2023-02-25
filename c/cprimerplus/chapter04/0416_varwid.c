/* varwid.c -- 使用变宽输出字段
 * printf() *放在转换字符之间时，*会占用一个参数
 */
#include <stdio.h>
int main(void)
{
	unsigned width,precision;
	int number = 256;
	double weight = 242.5;
	printf("Enter a field width:\n");
	scanf("%d",&width);
	// * 是 width， %*d 整体是number 即%6d 输出六位number,不足的使用空格
	printf("The number is :%*d\n",width,number);
	printf("Now enter a width and a precision:\n");
	scanf("%d %d",&width,&precision);
	printf("Weight = %*.*f\n",width,precision,weight);
	printf("Done!\n");
	return 0;
}
