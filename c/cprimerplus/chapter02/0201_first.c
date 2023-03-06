#include <stdio.h>
int main(void)// 一个简单的C程序
{
    int num;//定义一个名为num的变量
    num = 1;//为num赋值
    printf("I am a simple ");//使用printf()函数
    printf("computer.\n");
    printf("My favorite number is %d beacuse it is first.\n", num);
    return 0;
}
//gcc -E 0201_first.c -o 0201_first.i
//gcc -S 0201_first.i -o 0201_first.s
//gcc -c 0201_first.s -o 0201_first.o
//gcc 0201_first.o -o run.out