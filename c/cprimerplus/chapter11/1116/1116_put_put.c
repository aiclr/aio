/* put_put.c -- 用户自定义输出函数*/
#include <stdio.h>
#include "myput.h"

int main(void)
{
	put1("If I'd as much money");
	put1(" as I could spend,\n");
	printf("I count %d characters.\n",put2("I never would cry old chairs to mend."));
	return 0;
}
