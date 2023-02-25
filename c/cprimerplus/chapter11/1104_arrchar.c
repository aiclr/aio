/* arrchar.c --指针数组、字符串数组 */
#include <stdio.h>
#define SLEN 40
#define LIM 5

int main(void)
{
	//指针数组，内部保存的是指向静态内存中字符串字面量的指针，缺点是不能修改字面量的值
	const char *mytalents[LIM]=
	{
		"Adding numbers swiftly",
		"Multiplying accurately","Stashing data",
		"Following instructions to the letter",
		"Understanding the C language"
	};
	//二维char数组，每个字符串都被存储两次，优势是可以修改字面量的值
	char yourtalents[LIM][SLEN]=
	{
		"Walking in a straight line",
		"Sleeping","Watching television",
		"Mailing letters","Reading email"
	};

	int i;
	puts("Let's compare talents.");
	printf("%-36s %-25s\n","My Talents","Your Talents");
	for(i=0;i<LIM;i++)
		printf("%-36s %-25s\n",mytalents[i],yourtalents[i]);
	printf("\nsizeof mytalents:%zd,sizeof yourtalents:%zd\n",sizeof(mytalents),sizeof(yourtalents));
	return 0;
}
