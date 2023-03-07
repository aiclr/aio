/**
 * 创建一个整型变量toes，并将toes设置为10。程序中还要计算toes的两倍和toes的平方。该程序应打印3个值，并分别描述以示区分
 */
#include <stdio.h>

int main(void)
{
    int toes;
    toes=10;
    printf("toes = %d, %d x 2 = %d, %d^2 = %d.",toes,toes,toes<<1,toes,toes*toes);
}