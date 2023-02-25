/**
 * 用宏参数创建字符串
 *
 * 
 * subst.c -- 在字符串中替换
 * 
 * #define PSQR(X) printf("The square of X is %d.\n",((X)*(X)))
 * 双引号字符串中的X 被视为普通文本，不是一个可替换的记号
 *
 * C允许在字符串中包含宏参数
 * 在类函数宏的替换体中，#号作为一个预处理运算符，可以把记号转换为字符串
 * 如果x是一个宏形参，那么#x就是转换字符串"x"的形参名
 * 这个过程称为字符串化 stringizing
 */
#include <stdio.h>
#define PSQR(x) printf("The square of "#x" is %d.\n",((x)*(x)))
#define PSQR2(x) printf("22--The square of x is %d.\n",((x)*(x)))
#define PSQR3(x) printf("33--The square of #x is %d.\n",((x)*(x)))

int main(void)
{
	int y=5;
	/**
	 * #x 由"y"替换，字符串串联将三个相邻字符串组合成一个字符串 
	 * printf("The square of ""y"" is %d\n",((y)*(y)));
	 */
	PSQR(y);
	PSQR2(y);
	PSQR(2+4);
	PSQR2(2+4);
	PSQR3(3);
	PSQR3(2+4);
	printf("a""b""y\n");
	return 0;
}
