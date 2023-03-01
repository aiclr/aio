#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h> //提供 srand、rand、calloc、malloc、free、atoi
#include "../commons/head/random.h"

// 局部函数原型
static void bubble(int *arr, int length);

// 局部函数定义
void bubble(int *arr, int length)
{
    bool b = true;
    for (int i = 1; i < length && b; i++)
    {
        b = false;
        for (int j = 0; j < length - i; j++)
        { // 使用下标访问
            if (compare(arr[j], arr[j + 1]))
            {
                swap(&arr[j], &arr[j + 1]);
                b = true;
            }
        }
        show(arr, length);
    }
}

// gcc -o run.out bubble_sort.c ../commons/source/random.c
// ./run.out 100
int main(int argc, const char *argv[])
{
    // string 转 int
    // extern int atoi (const char *__nptr)
    int length = atoi(argv[1]);

    int *mock = randomArray(length);
    bubble(mock, length);
    free(mock); // stdlib.h
}
