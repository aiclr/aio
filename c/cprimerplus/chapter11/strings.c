#include <stdio.h>
void main(void)
{
    char str1[10] = "first";
    char str2[10] = "second";
    char str3[20];
    char *src, *dst;
    src = str1;
    dst = str3;
    while (*src != 0)
    {
        *dst = *src;
        src++;
        dst++;
    }
    src = str2;
    while (*src != 0)
    {
        *dst = *src;
        src++;
        dst++;
    }
    *dst = 0;
    printf("%s+%s=%s\n", str1, str2, str3);
}