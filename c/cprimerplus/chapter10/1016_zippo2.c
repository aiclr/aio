/* zippo2.c -- 通过指针获取zippo的信息*/
#include <stdio.h>

int main(void)
{
	int zippo[4][2]={{2,4},{6,8},{1,3},{5,7}};
	int(*pz)[2];
	pz=zippo;
	printf("数组的数组\n zippo是大小为4的数组 zippo[0]是大小为2的数组 pz = zippo = %p,pz+1=%p\n",pz,pz+1);
	printf("pz[0] 是大小为2的数组的起始地址=%p,pz[0]+1=%p\n",pz[0],pz[0]+1);
	printf("*pz = pz[0] = %p,*pz+1=pz[0]+1=%p\n",*pz,*pz+1);
	printf("pz[0][0]=%d\n",pz[0][0]);
	printf(" *pz[0]=%d\n",*pz[0]);
	printf(" **pz=%d\n",**pz);
	printf("    pz[2][1]=%d\n",pz[2][1]);
	printf("*(*(pz+2)+1)=%d\n",*(*(pz+2)+1));
	
	printf("指针兼容性\n");
	const int **pp2;//指向int指针的指针
	int *p1;//指向int的指针
	const int n=13;
	pp2=&p1;//导致const int **pp2 的const失效
	*pp2=&n;//导致p1指向n
	printf("pp2=%p,*pp2=%p,p1=%p\n",pp2,*pp2,p1);
	printf("p1=%p,&n=%p\n",p1,&n);
	*p1=10;//此时n值无法确定结果,不同编译器n结果不一样 gcc n=13 和clang n=10
	printf("n=%d\n",n);
	return 0;
}
