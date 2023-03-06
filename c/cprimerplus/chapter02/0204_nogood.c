#include <stdio.h>
/* 0204_nogood.c 有语法错误的程序*/
int main(void)
{ /* 只能使用花括号 */
    int n, n2, n3;
    /**
     * 也可以这样声明
     * int n;
     * int n2;
     * int n3;
     * 
     * 不允许 int n, int n2, int n3;
     */
    n = 5;/* 注释要有头尾 */
    n2 = n * n;
    n3 = n2 * n;
    printf("n = %d, n squared = %d, n cubed = %d\n", n, n2, n3); /* 分号结尾别忘记 */
    return 0;
}