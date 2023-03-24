#include "shared/swap.h"

/**
 * 动态库
 * 引用传递，不引入第三变量，交换值
 * @param a
 * @param b
 */
void swap(int *a,int *b) {
    *a^=*b;
    *b^=*a;
    *a^=*b;
}
