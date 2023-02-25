/* rules.c -- 优先级测试 */
#include <stdio.h>
int main(void)
{
	int top,score;
	/**
	 * = 运算符的结合律 执行顺序是从右往左
	 * 二元运算符的结合律 + - 执行顺序从左往右
	 * 一元运算符的结合律 + - 执行顺序从右往左
	 * () 结合律执行顺序从左往右
	 **/
	top = score = -(2 + 5) * 6 + (4 + 3 * (2 + 3));
	printf("top = %d, score = %d\n", top,score);
	return 0;
}
