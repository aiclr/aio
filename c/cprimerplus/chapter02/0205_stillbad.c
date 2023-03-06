/* 0205_stillbad.c 语义错误 */
#include <stdio.h>

int main(void)
{
    int n, n2, n3;
    n = 5;
    n2 = n * n;
    /* 求立方 此处语义错误 */
    n3 = n2 * n2;
    printf("n = %d, n squared = %d, n cubed = %d\n", n, n2, n3);
    return 0;
}