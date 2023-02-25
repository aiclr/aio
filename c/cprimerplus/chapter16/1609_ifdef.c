/**
 * #undef 指令用于取消 #define指令 重新定义新值
 * 
 * #define 宏作用域从它在文件中的声明处开始，直到#undef指令取消宏为止，或延伸至文件尾（以二者中选满足的条件作为宏作用域的结束）
 * 如果宏通过头文件引入，那么#define在文件中的位置取决于#include指令的位置
 *
 * __DATE__和__FILE__不能取消的预定义宏
 *
 * 条件编译 conditinal compilation
 * 可以使用这些指令告诉编译器根据编译时的条件执行或忽略信息或代码块
 *
 *
 * #ifdef、#else、#endif
 *
 * #ifdef 如果预处理器已定义后面的标识符，则执行#else或#endif指令之前的所有指令并编译所有C代码（先出现哪个指令就执行到哪里）
 * 如果预处理器未定义，且有#else指令，则执行#else和#endif指令之间的所有代码
 *
 */
#include <stdio.h>
//#define JUST_CHECKING
#define LIMIT 4
int main(void)
{
	int i;
	int total=0;
	for(i=1;i<=LIMIT;i++)
	{
		total+=2*i*i+1;
#ifdef JUST_CHECKING
		printf("i=%d,running total=%d\n",i,total);
#endif
	}
	printf("Grand total=%d\n",total);
	return 0;
}
