#include <stdio.h>

#define LENGTH 10

int main(void)
{

    int mock[LENGTH] = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

    for (int i = 0; i < LENGTH; i++)
    {
        for (int j = 0; j < LENGTH - 1 - i; j++)
        {
            if (mock[j] > mock[j + 1])
            {
                mock[j] = mock[j] ^ mock[j + 1];
                mock[j + 1] = mock[j] ^ mock[j + 1];
                mock[j] = mock[j] ^ mock[j + 1];
            }
        }
    }
    for (int i = 0; i < LENGTH; i++)
    {
        printf("%d ", mock[i]);
    }
    println();
}