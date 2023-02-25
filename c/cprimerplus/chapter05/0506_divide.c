/* divide.c -- 除法演示*/
#include <stdio.h>
int main(void)
{
	printf("integer division: 5/4 is %d\n",5/4); //整数除法会截断计算结果的小数部分，不会四舍五入
	printf("integer division: 6/3 is %d\n",6/3); 
	printf("integer division: 7/4 is %d\n",7/4);
	printf("integer division: 7./4. is %1.2f\n",7./4.);
	/**
	 * 混合整数和浮点数计算结果是浮点数
	 * 计算机不能真正用浮点数除以整数
	 * 编译器会把两个运算对象转换成相同类型，4被转换成浮点数;
	 */
	printf("integer division: 7./4 is %1.2f\n",7./4); 
	return 0;
}
