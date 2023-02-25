/**
 * align.c -- 使用_Alignof _Alignas(C11) 对齐
 * C11的对齐特性比用位填充字节更自然，代表C在处理硬件相关问题上的能力
 * 对齐指的是如何安排对象在内存中的位置
 * 为了效率最大化，系统可能要把一个double类型的值储存在4byte内存地址上
 * 却允许把char储存在任意地址
 * 
 * 以下情况受益于对齐控制:
 * 	把数据从一个硬件位置转移到另一个位置
 * 	调用指令同时操作多个数据项
 *
 * _Alignof运算符给出一个类型的对齐要求，在关键字_Alignof后面的圆括号内写上类型名即可
 * size_t d_align=_Alignof(float);
 * 假设d_align=4,意思是float类型对象的对齐要求是4,也就是说，4是储存该类型值相邻地址的字节数
 * 一般而言，对齐值都应该是2的非负整数次幂
 * 较大的对齐值被称为stricter 或stronger
 * 较小的对齐值被称为weaker
 *
 * 可以使用_Alignas说明符指定一个变量或类型的对齐值
 * 但是不应该要求该值小于基本对齐值
 * 如：
 * 	float 基本对齐值4,不能请求其对齐值是1或2
 * 
 * _Alignas作为声明的一部分指定对齐值,说明符后面的圆括号内包含对齐值或类型
 * _Alignas(double) char c1;
 * _Alignas(8) char c2;
 * unsigned char _Alignas(long double) c_arr[sizeof(long double)];
 * 注意Clang 3.2版本 要求_Alignas(type) 在类型说明符后面如上一行代码所示
 * 但是无论_Alignas(type)说明符在类型说明符的前面还是后面，GCC4.7.3都能识别，后来Clang3.3版本也支持了这两种顺序
 *
 * C11在stdlib.h库添加了一个新的内存分配函数，用于对齐动态分配的内存
 * void *aligned_alloc(size_t alignment,size_t size);
 * 	alignment代表指定的对齐
 * 	size是所需的字节数，值应是第一个参数的倍数，
 * 	与其他内存分配函数一样，要使用free()释放分配的内存
 *
char alignment: 1 char 对齐值是1,对于普通的char类型变量，编译器可以使用任何地址
double alignment: 8 double 对齐值是8 这意味着地址的类型对齐可以被8整除，以0或8结尾的十六进制地址可被8整除
&dx: 0x7ffc70014028
&ca: 0x7ffc70014025
&cx: 0x7ffc70014026
&dz: 0x7ffc70014030
&cb: 0x7ffc70014027
&cz: 0x7ffc70014020 手动设置了cz对齐值8,分配给cz的地址会以0,8结尾，但是cz的大小还是char的大小1字节


 */
#include <stdalign.h> //可以把alignas和alignof分别作为_Alignas _Alignof的别名可以与C++关键字匹配
#include <stdio.h>
int main(void)
{
	double dx;
	char ca;
	char cx;
	double dz;
	char cb;
	char _Alignas(double) cz;
	printf("char alignment: %zd\n",_Alignof(char));
	printf("double alignment: %zd\n",_Alignof(double));
	printf("&dx: %p\n",&dx);
	printf("&ca: %p\n",&ca);
	printf("&cx: %p\n",&cx);
	printf("&dz: %p\n",&dz);
	printf("&cb: %p\n",&cb);
	printf("&cz: %p\n",&cz);
	return 0;
}
