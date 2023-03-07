#include <stdio.h>

void smile(void);

int main(void)
{
    int i,j;
    i = 3;
    while (i>0)
    {
        j=i;
        while (j>0)
        {
            smile();
            j--;
        }
        printf("\n");
        i--;
    }
    return 0;
}

void smile(void)
{
    printf("Smile!");
}
