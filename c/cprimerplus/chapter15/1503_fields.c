/**
 * fields.c -- 定义并使用字段
 * 一位即可表示三原色之一，其他颜色使用三原色的组合来表示，如紫色由打开的蓝色位和红色位组成，所以紫色可以表示为 BLUE|RED
 *
 * 位字段：操控位的方法 bit field
 * 	位字段是一个或多个（取决于字段的大小）signed int 或unsigned int类型变量中的一组相邻的位（C99和C11新增了_Bool类型的位字段）
 * 	位字段通过一个结构声明来建立
 * 	该结构声明为每个字段提供标签，并确定该字段的宽度
 * 
 * 字段储存在一个int中的顺序取决于机器
 * 一些机器存储顺序是从左往右
 * 一些机器存储顺序是从右往左
 * 不同机器中两个字段边界的位置也有区别，由于这些原因，位字段通常都不容易移植
 * 以特定硬件设备所用的形式储存数据，需要用到这种不可移植的特性
 */
#include <stdio.h>
#include <stdbool.h> //C99定义了bool、true、false
//线的样式
#define SOLID 0
#define DOTTED 1
#define DASHED 2
//三原色 红 绿 蓝
#define BLUE 4
#define GREEN 2
#define RED 1
//混合色
#define BLACK 0
#define YELLOW (RED|GREEN)
#define MAGENTA (RED|BLUE)
#define CYAN (GREEN|BLUE)
#define WHITE (RED|GREEN|BLUE)

//示例 4个1位的字段，占用一个int大小的内存单元，但是本例只使用其中4位
//prnt 包含4个1位的字段可以通过普通的结构成员运算符 . 单独给这些字段赋值
//prnt.itals=0;
//prnt.undln=1;
//每个字段恰好为1位 1bit 所以只能为其赋值1或0
struct
{
	unsigned int autfd:1;
	unsigned int bldfc:1;
	unsigned int undln:1;
	unsigned int itals:1;
} prnt;
/**
 * 内含位字段的结构可以使用多位来表示
 * 确保所赋值不超出字段可容纳范围
 */
struct
{
	unsigned int code1:2;//2bit 可存储00,01,10,11
	unsigned int code2:2;
	unsigned int code3:8;//8bit 存储 0~ 2^8-1
} prcode;
/**
 * 如果声明的总位数，超过一个unsigned int类型的大小，会用到下一个unsigned int类型的存储位置
 * 一个字段不允许跨越两个unsigned int之间的边界
 * 编译器会自动移动跨界的字段，保持unsigned int的边界对齐，
 * 一旦发生这种情况，第一个unsigned int中会留下一个未命名的洞（部分无法使用的位）
 * 使用未命名的字段宽度填充未命名的洞。使用一个宽度位0的未命名字段迫使下一个字段与下一个整数对齐
 */
struct 
{
	unsigned int field1:1;
	unsigned int       :2;//2bit的空隙
	unsigned int field2:1;
	unsigned int       :0;//0迫使下一个字段与下一个整数对齐
	unsigned int field3:1;//由于上面设置了宽度为0的未命名字段，所以field3将储存在下一个unsigned int中
}


//初始化位字段结构与初始化普通结构的语法相同
//每个索引对应一个表示颜色的字符串，每种颜色都把索引值作为该颜色的数值
//index=1 对应字符串red,枚举常量red的值是1
const char *colors[8]={"black","red","green","yellow","blue","magenta","cyan","white"};

struct box_props
{
	bool opaque:1;//或者 unsigned int(C99以前)
	unsigned int fill_color:3;
	unsigned int           :4;
	bool show_border:1;
	unsigned int border_color:3;
	unsigned int border_style:2;
	unsigned int             :2;
};
void show_settings(const struct box_props *);

int main(void)
{
	//创建并初始化box_props结构
	struct box_props box={true,YELLOW,true,GREEN,DASHED};
	printf("Original box setting:\n");
	show_settings(&box);
	//给位字段成员赋值
	box.opaque=false;
	box.fill_color=WHITE;
	box.border_color=MAGENTA;
	box.border_style=SOLID;
	printf("\nModified box settings:\n");
	show_settings(&box);
	return 0;
}

void show_settings(const struct box_props *bp)
{
	printf("Box is %s.\n",bp->opaque==true?"opaque":"transparent");
	printf("The fill color is %s.\n",colors[bp->fill_color]);
	printf("Border %s.\n",bp->show_border==true?"shown":"not shown");
	printf("The border color is %s.\n",colors[bp->border_color]);
	printf("The border style is ");
	//switch 语句也可以使用位字段成员，甚至还可以把位字段成员用作数组的下标
	switch(bp->border_style)
	{
		case SOLID:printf("solid.\n");break;
		case DOTTED:printf("dotted.\n");break;
		case DASHED:printf("dashed.\n");break;
		default:printf("unknown type.\n");
	}
}
