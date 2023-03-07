#include <stdio.h>
/* 0202_fathm_ft.c 把2英寻转换成英尺
 * 英寻，也称浔。航海用的深度单位，通常用在海图上测量水深。
 * 1英寻(fathom)=1/880英里(mile)=2码(yard)=6英尺(feet)=72英寸(inch)=1.829米(metre)
 */
int main(void)
{
    int feet, fathoms;
    fathoms = 2;
    feet = 6 * fathoms;
    printf("There are %d feet in %d fathoms!\n", feet, fathoms);
    printf("Yes, I said %d feet!\n", 6 * fathoms);
    return 0;
}