#include <stdlib.h>
#include "../commons/head/random.h"

static void shell(int *arr, int length);

void shell(int *arr, int length)
{
    // temp 临时保存待插入元素
    int temp;
    int j;
    for(int gap=length/2;gap>0;gap/=2)
    {
        for(int i=gap;i<length;i++)
        {
            if(arr[i]<arr[i-gap])
            {
                j=i;
                temp=arr[i];
                while(j-gap>=0 && temp<arr[j-gap])
                {
                    arr[j]=arr[j-gap];
                    j-=gap;
                }
                arr[j]=temp;
                show(arr, length);
            }
        }
    }
}
// gcc -o run.out shell_sort.c ../commons/source/random.c
// ./run.out 10
int main(int argc, const char *argv[])
{
    int length = atoi(argv[1]);
    int *mock = randomArray(length);
    show(mock, length);
    shell(mock, length);
    free(mock);
}