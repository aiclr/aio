/* zippo1.c -- 多维数组 内存位置是连续数组 */
#include <stdio.h>

int main(void)
{
	int zippo[4][2] = {{2,4},{6,8},{1,3},{5,7}};
	printf("sizeof int=%zd\n",sizeof(int));
	printf("zippo指向zippo[0]，指向的是一个大小为两个int的数组");
	printf("*zippo=zippo[0]");
	printf("zippo[0]指向zippo[0][0]，指向的是一个int类型数据");
	printf("**zippo=*zippo[0]==zippo[0][0]");
	printf("指向二维数组第一个一维数组的指针zippo= %p,zippo指向包含两个int的一维数组，移动一个一维数组数组，即移动两个int,地址+8，zippo+1=zippo[1]=%p\n",zippo,zippo+1);
	printf("指向二维数组的第一个数组的第一个元素的指针zippo[0]=zippo[0][0]=%p,zippo[0]指向一个int，+1移动4bit=zippo[0]+1=zippo[0][1]=%p\n",zippo[0],zippo[0]+1);
	printf("*zippo=zippo指向的二维数组的第一个元素的指针=zippo[0]=%p,*zippo+1=%p\n",*zippo,*zippo+1);
	printf("zippo[0][0]=%d\n",zippo[0][0]);
	printf("*zippo[0]=%d\n",*zippo[0]);
	printf("**zippo=%d\n",**zippo);
	printf("zippo[2][1]=%d\n",zippo[2][1]);
	printf("*(*(zippo+2)+1)=%d\n",*(*(zippo+2)+1));
	return 0;
}
