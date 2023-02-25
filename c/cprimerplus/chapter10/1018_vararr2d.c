/* vararr2d.c -- 变长数组作为参数的函数 注意变长指的是参数里的行列可以改变，而不是数组大小可以改变，行和列确定后,内存分配完毕，数组大小不能改变 */
#include <stdio.h>
#define ROWS 3
#define COLS 4
//VAL 变长数组 指的是ar指向的数组可以变
int sum2d(int rows,int cols,int ar[rows][cols]);
//int sum2d(int,int,int ar[*][*]);
int main(void)
{
	int i,j;
	int rs=3;
	int cs=10;
	int junk[ROWS][COLS]=
	{
		{2,4,6,8},
		{3,5,7,9},
		{12,10,8,6}
	};
	int morejunk[ROWS-1][COLS+2]=
	{
		{20,30,40,50,60,70},
		{5,6,7,8,9,10}
	};
	int var[rs][cs];
	for(i=0;i<rs;i++)
		for(j=0;j<cs;j++)
			var[i][j]=i*j+j;
	printf("3X5 array\n");
	printf("Sum of all elements = %d\n",sum2d(ROWS,COLS,junk));
	printf("2X6 array\n");
	printf("Sum of all elements = %d\n",sum2d(ROWS-1,COLS+2,morejunk));
	printf("3X10 array\n");
	printf("Sum of all elements = %d\n",sum2d(rs,cs,var));
	return 0;
}

int sum2d(int r,int c, int ar[r][c])
{
	int tot=0;
	for(int i=0;i<r;i++)
	{
		for(int j=0;j<c;j++)
		{
			tot+=ar[i][j];
		}
	}
	return tot;
}
