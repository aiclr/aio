#include <stdio.h>

int main(void)
{
    int dogs;
    printf("How many dogs do you have?\n");
    scanf("%d", &dogs);
    printf("So you have %d dog(s)!", dogs);
    return 0;
}
// gcc -E 0101.c -o 0101.i 预处理
// gcc -S 0101.i -o 0101.s 汇编
// gcc -c 0101.s -o 0101.o 编译
// gcc 0101.o -o run.out 链接
//
//gcc -o run.out 0101.c