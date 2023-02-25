/**
 * doubincl.c 包含两次1610_names.h
 */
#include <stdio.h>
#include "1610_names.h"
#include "1610_names.h"//不小心二次包含头文件
int main(void)
{
	names winner={"Less","Ismoor"};
	printf("The winner is %s %s.\n",winner.first,winner.last);
	return 0;
}
