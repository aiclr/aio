#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h> //提供 srand、rand、calloc、malloc、free、atoi
#include "../commons/head/random.h"

// 局部函数原型
static void selection(int *arr,int length);

// 局部函数定义
void selection(int *arr,int length)
{
    for(int i=0;i<length-1;i++)
    {
        int *min_point=arr+i;
        for(int j=i+1;j<length;j++)
        {
            if(compare(*min_point,*(arr+j)))
            {
                min_point=arr+j;
            }
        }
        if(min_point!=arr+i)
        {
            swap(min_point,arr+i);
            show(arr,length);
        }
    }
}

// gcc -o run.out selection_sort.c ../commons/source/random.c
// ./run.out 100
int main(int argc, const char *argv[])
{
    // string 转 int
    // extern int atoi (const char *__nptr)
    int length = atoi(argv[1]);

    int *mock = randomArray(length);
    show(mock,length);
    selection(mock, length);
    free(mock); // stdlib.h
}