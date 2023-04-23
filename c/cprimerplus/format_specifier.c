#include <stdio.h>

int main(void)
{
    /**
     * %d 将整数值作为带符号的十进制整数，即它将负值和正值一起使用，但值应该是十进制的，否则它将打印垃圾值
     * 如果输入是八进制格式，如：012，则%d将忽略0并将输入作为12
     *
     * %i 将整数值作为十进制、十六进制或八进制类型的整数值 八进制012 十六进制0x12
     */
    int a,b,c;
    printf("Enter 12 of a in decimal format:");
    scanf("%d",&a);
    printf("Enter 012 of a in decimal format:");
    scanf("%i",&b);
    printf("Enter 0x12 of a in decimal format:");
    scanf("%i",&c);
    printf("a=%i,b=%i,c=%i\n",a,b,c);
    printf("a=%d,b=%d,c=%d\n",a,b,c);

    /* TODO %a %A*/
    printf("0.5 %%a = %a\n",0.5);
    printf("0.5 %%A = %A\n",0.5);
}
