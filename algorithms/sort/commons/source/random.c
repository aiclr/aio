#include <stdio.h> // 提供 printf
#include <stdlib.h> //提供 srand、rand、calloc、malloc、free
#include <time.h>   //提供 time
#include <stdbool.h> // 提供 bool、true、false。C把所有非零数值都视为真

// 局部函数定义
void swap(int *first, int *next)
{
    *first = *first ^ *next;
    *next = *first ^ *next;
    *first = *first ^ *next;
}

bool compare(int first, int next)
{
    return first > next;
}

void show(const int *arr, int length)
{
    for (int i = 0; i < length; i++)
    {
        // 使用指针访问数组
        printf("%d ", *(arr + i));
    }
    printf("\n");
}

int *randomArray(int length)
{
    // 利用时钟系统初始化随机数种子值
    // ANSI C time()函数返回系统时间，返回类型 time_t
    // 参数为 time_t类型对象的地址，时间值存储在传入的地址上，传入空指针0,只能通过time()函数返回值来提供随机值
    srand((unsigned int)time(0));
    // malloc() 分配内存
    // 	参数: 所需的内存字节数 可以使用 2*sizeof(int)计算字节数
    // 	返回: 寻找合适大小的空闲内存块,此时这部分内存是匿名的,然后返回内存块的首字节地址
    // 	失败: 可能没有合适大小的空闲内存块，无法分配内存，将返回空指针，此时可以调用exit() 结束程序
    // calloc() 分配内存
    //      参数: 两个无符号整数 unsigned int ;
    //            第一个参数是所需的存储单元数量
    //            第二个参数是存储单元的大小(单位字节)
    //      返回: 同malloc()
    // free() 释放 malloc() 或 calloc() 分配的内存
    // 	参数: malloc() 或 calloc() 返回的内存块首字节地址
    // exit()结束程序，EXIT_FAILURE 或 0 表示程序结束
    int *arr = (int *)calloc(length, sizeof(int));
    // int *arr=(int *)malloc(length*sizeof(int));
    for (int i = 0; i < length; i++)
    {
        // length 以内随机数
        arr[i] = rand() % length + 1; // stdlib.h
    }
    return arr;
}