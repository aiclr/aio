/* post_pre.c -- i++ & ++i 区别 */
#include <stdio.h>
int main(void)
{
	int i,j;
	i=j=1;
	int p=i++;
	int q=++j;
	printf("i=%d,p=%d,j=%d,q=%d\n",i,p,j,q);
	return 0;
}
