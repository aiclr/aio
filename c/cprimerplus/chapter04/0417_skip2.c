/* skiptwo.c --跳过输入中的前两个整数 */
#include <stdio.h>
int main(void)
{
	int n;
	printf("Please enter three integers:\n");
	// scanf() 中 * 放在%和转换字符之间，会使得scanf() 跳过相应的输入项：读取文件中特定列的内容是，此跳过功能很有用
	scanf("%*d %*d %d",&n);
	printf("The last integer was %d\n",n);
	return 0;
}
