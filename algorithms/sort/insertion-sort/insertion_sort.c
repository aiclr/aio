#include <stdlib.h>
#include "../commons/head/random.h"

static void insertion(int *arr, int length);

void insertion(int *arr, int length)
{
    // 临时保存待插入元素
    int temp;
    //指针 指向有序数组最后一个元素
    int *index;

    for (int i = 1; i < length; i++)
    {
        if (arr[i] > arr[i - 1])
            continue;

        index = &arr[i - 1];
        temp = arr[i];

        while (index >= &arr[0] && temp < *index)
        {
            *(index + 1) = *index;
            index--;
        }
        *(index+1) = temp;
        show(arr, length);
    }
}
// gcc -o run.out insertion_sort.c ../commons/source/random.c
// ./run.out 10
int main(int argc, const char *argv[])
{
    int length = atoi(argv[1]);
    int *mock = randomArray(length);
    insertion(mock, length);
    free(mock);
}