#include <stdio.h>
#include <stdlib.h> //提供 srand、rand、calloc、malloc、free、atoi
#include "../commons/head/random.h"

// 局部函数原型
static void quick(int *arr, int start, int end, int length);
// 使用指针快排
static void quick_pointer(int *arr, int length);

// 使用指针快排
void quick_pointer(int *arr, int length)
{
    // 创建指向int 指针 i,j 相当于拷贝了一份数组
    int *i = arr, *j = arr;
    while (j < arr + length - 1)
    {
        // 比较 arr末尾值 (arr+length-1)指向末尾值的指针 与 j指向的值大小
        if (compare(*(arr + length - 1), *j))
        {
            if (i != j)
                swap(i, j);
            i++;
        }
        j++;
        show(arr, length);
    }
    if (i != j)
        swap(i, arr + length - 1);
    show(arr, length);
    if (i > arr + 1)
        quick_pointer(arr, i - arr);
    if (i < arr + length - 2)
        quick_pointer(i + 1, (arr + length - 1) - i);
}

// 局部函数定义
void quick(int *arr, int start, int end, int length)
{
    int *pivot = arr + end;
    int i = start, j = start;
    while (j < end)
    {
        if (compare(*pivot, arr[j]))
        {
            if (i != j)
                swap(&arr[i], &arr[j]);
            i++;
        }
        j++;
        show(arr, length);
    }
    if (i != j)
        swap(&arr[i], pivot);
    show(arr, length);
    if (i > start + 1)
        quick(arr, start, i - 1, length);
    if (i < end - 1)
        quick(arr, i + 1, end, length);
}

// gcc -o run.out quick_sort.c ../commons/source/random.c
// ./run.out 100
int main(int argc, const char *argv[])
{
    // string 转 int
    // extern int atoi (const char *__nptr)
    int length = atoi(argv[1]);
    int *mock = randomArray(length);
    show(mock, length);
    quick(mock, 0, length - 1, length);
    show(mock, length);
    free(mock); // stdlib.h

    printf("\n");

    int *mock2 = randomArray(length);
    show(mock2, length);
    quick_pointer(mock2, length);
    show(mock2, length);
    free(mock2); // stdlib.h
}