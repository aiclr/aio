/* flc.c -- 复合字面量 
 *
 * 典型用法: 把信息传入函数前不必先创建数组
 * */

#include <stdio.h>
#define COLS 4
//复合字面量作为参数 ar是一个COLS列的数组，具体几行不确定，根据实际情况来定几行和rows
int sum2d(const int ar[][COLS],int rows);
//ar是一个int数组
int sum(const int ar[],int n);
int main(void)
{
	int total1,total2,total3;
	int *pt1;
	pt1=(int[2]){10,20};
	int (*pt2)[4];
	pt2=(int[2][COLS]){{1,2,3,-9},{4,5,6,-8}};
	total1=sum(pt1,2);
	total2=sum2d(pt2,2);
	total3=sum((int[]){4,4,4,5,5,5},6);
	printf("total1 = %d\n",total1);
	printf("total2 = %d\n",total2);
	printf("total3 = %d\n",total3);
	return 0;
}

int sum2d(const int ar[][COLS],int rows)
{
	int tot=0;
	for(int r=0;r<rows;r++)
	{
		for(int c=0;c<COLS;c++)
		{
			tot+=ar[r][c];
		}
	}
	return tot;
}

int sum(const int ar[],int n)
{
	int tot=0;
	for(int i=0;i<n;i++)
	{
		tot+=ar[i];
	}
	return tot;
}
	
