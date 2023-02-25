/* where.c -- 数据被储存在何处 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int static_store = 30;//静态变量，编译时确定内存大小，程序开始执行时被创建，程序结束时被销毁
const char* pcg = "String Literal";//静态变量
int main(void)
{
	int auto_store = 40;//自动变量，程序进入变量定义所在块时存在，在程序离开块时消失，这部分内存通常使用栈stack处理，速度较快
	char auto_string[] = "Auto char Array";//自动变量
	int *pi;
	char *pcl;
	pi=(int*)malloc(sizeof(int));//动态分配，动态内存比较分散，类似java堆，动态内存比栈内存慢
	*pi=35;
	pcl=(char*)malloc(strlen("Dynamic String")+1);
	strcpy(pcl,"Dynamic String");
	printf("static_store: %d at %p\n",static_store,&static_store);
	printf("auto_store: %d at %p\n",auto_store,&auto_store);
	printf("*pi: %d at %p\n",*pi,pi);
	printf("%s at %p\n",pcg,pcg);
	printf("%s at %p\n",auto_string,auto_string);
	printf("%s at %p\n",pcl,pcl);
	printf("%s at %p\n","Quoted String","Quoted String");
	free(pi);
	free(pcl);
	return 0;
}
