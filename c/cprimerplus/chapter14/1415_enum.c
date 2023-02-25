/**
 * enum.c -- 使用枚举类型
 * 枚举类型 enumerated type 
 * 可以声明符号名称来表示整型常量 关键字 enum
 * enum 常量实际是int 类型，因此只要能使用int类型的地方就可以使用枚举类型
 * 枚举类型的目的是提高程序的可读性、可维护性，语法与结构struct语法相同
 * 
 * 声明创建spectrum作为标记名，允许把enum spectrum作为一个类型名使用
 * 花括号内的标识符枚举了spectrum变量可能有的值,称为枚举符
 * enum spectrum {red,orange,yellow,green,blue,violet};
 * 
 * 使用color作为该类型的变量，color可能的值是red,orange,yellow,green,blue,violet
 * enum spectrum color;
 *
 * int c;
 * color=blue;
 * if(color==yellow)
 * ...
 * for(color=red;color<=violet;color++)
 * ...
 *
 * 枚举符是int类型，但是枚举变量可以是任意整数类型，前提是该整数类型可以储存枚举变量
 * spectrum枚举符范围是0～5所以编译器可以用unsigned char来表示color变量
 *
 * C枚举的一些特性并不适用于C++
 * C允许枚举变量使用++运算符，但是C++标准不允许
 * 如果编写的代码将来会并入C++程序那么必须把上面例子中的color声明为int类型
 *
 *
 * 枚举符为一个有名称的常量，
 * red=0
 * orange=1
 * yellow=2
 * green=3
 * blue=4
 * violet=5
 * 只要是能使用整型常量的地方就可以使用枚举常量
 * 1. 如声明数组时可以用枚举常量表示数组的大小
 * 2. 在switch语句中可以把枚举常量作为标签
 *
 * 默认值
 * 默认情况枚举列表中的常量都被赋予0,1,2等
 *
 * 赋值
 * enum levels {low=100,medium=500,high=2000};
 * enum feline {cat,lynx=10,puma,tiger};
 * cat=0,lynx=10,puma=11,tiger=12
 *
 *
 * c语言使用namespace标识程序中的各部分，即通过名称来识别
 * 作用域是namespace概念的一部分
 *   两个不同作用域的同名变量不冲突
 *   两个相同作用域的同名变量冲突
 * namespace是分类别的，在特定作用域中的结构标记、联合标记、枚举标记都共享相同的namespace，该namespace与普通变量使用的namespace不同
 * 在相同作用域中变量和标记的名称可以相同，不会冲突，但是不能在相同作用域中声明两个同名标签或同名变量
 * struct rect {double x,double y;};
 * int rect;
 * 上面两行在C中不会产生冲突（以两种不同的方式使用相同的标识符会造成混乱）
 *
 * C++不允许这么做，因为C++把标记名和变量名放在相同的namespace
 *
 *
 */
#include <stdio.h>
#include <string.h>//提供strcmp()和strchr() 函数原型
#include <stdbool.h>//C99特性

char *s_gets(char *,int);
enum spectrum {red,orange,yellow,green,blue,violet};
const char *colors[]={"red","orange","yellow","green","blue","violet"};

#define LEN 30

int main(void)
{
	char choice[LEN];
	enum spectrum color;
	bool color_is_found=false;
	puts("Enter a color (empty line to quit):");
	while(s_gets(choice,LEN)!=NULL && choice[0]!='\0')
	{
		for(color=red;color<=violet;color++)
		{
			if(strcmp(choice,colors[color])==0)
			{
				color_is_found=true;
				break;
			}
		}
		if(color_is_found)
			switch(color)
			{
				case red:
					puts("Roses are red.");
					break;
				case orange:
					puts("Poppies are orange.");
					break;
				case yellow:
					puts("Sunflowers are yellow.");
					break;
				case green:
					puts("Grass is green.");
					break;
				case blue:
					puts("Bluebells are blue.");
					break;
				case violet:
					puts("Violets are violet.");
					break;
			}
		else
			printf("I don't know about the color %s.\n",choice);
		color_is_found=false;
		puts("Next color,please(empty line to quit):");
	}
	puts("Goodbye!");
	return 0;
}
char *s_gets(char *st,int n)
{
	char *ret_val;
	char *find;
	ret_val=fgets(st,n,stdin);
	if(ret_val)
	{
		find=strchr(st,'\n');
		if(find)
			*find='\0';
		else
			while(getchar()!='\n')
				continue;
	}
	return ret_val;
}
