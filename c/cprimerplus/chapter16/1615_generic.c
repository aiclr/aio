/**
 * 基本的浮点型数学函数接受double类型的参数,并返回double类型的值
 * 可以把float或long double 类型的参数传递给这些函数,仍能正常工作
 * 因为这些类型的参数会被转换为 double类型.看似很方便,并不是最好的处理方式.
 * 如果不需要双精度,单精度值来计算更快
 * long double 转换为double 会丢失精度.
 * 为解决这些潜在问题,C标准专门为float类型和long double 类型提供标准函数
 * 即在原始函数名前加上f 或 l前缀
 * sqrtf()是sqrt()的float版本
 * sqrtl()是sqrt()的long double版本
 * 利用C11 新增的泛型选择表达式定义一个泛型宏 根据参数类型选择合适的数学函数版本
 *
 *
 * SIN() 中 _Generic 表达式的值是一个特定的函数调用.
 * 函数调用在泛型选择表达式内部
 *
 *
 *
 * SQRT() 定义更简洁
 * _Generic表达式的值就是函数名,如sinf.函数的地址可以代替该函数名
 * 所以_Generic 表达式的值是一个指向函数的指针
 * 紧随整个_Generic表达式之后的是(X) ,
 * ** 函数指针(参数) ** 格式表示函数指针,因此这是一个带指定的参数的函数指针.
 * 即: 先对泛型选择表达式求值得到一个指针,然后通过该指针调用它所指向的函数
 */
#include <stdio.h>
#include <math.h>
#define RAD_TO_DEG (180/(4*atanl(1)))
//泛型平方根函数
#define SQRT(X) _Generic((X),\
		long double:sqrtl,\
		default:sqrt,\
                float:sqrtf)(X)
// 泛型正弦函数,角度的单位为度
#define SIN(X) _Generic((X),\
		long double: sinl((X)/RAD_TO_DEG),\
		default: sin((X)/RAD_TO_DEG),\
                float: sinf((X)/RAD_TO_DEG)\
		)
int main(void)
{
	float x= 45.0f;
	double xx= 45.0;
	long double xxx=45.0L;
	long double y=SQRT(x);
	long double yy=SQRT(xx);
	long double yyy=SQRT(xxx);
	printf("%.17LF\n",y);//匹配float
	printf("%.17LF\n",yy);//匹配double
	printf("%.17LF\n",yyy);//匹配long double
	int i=45;
	yy=SQRT(i);//匹配default
	printf("%.17LF\n",yy);
	yyy=SIN(xxx);//匹配long double
	printf("%.17LF\n",yyy);
	return 0;
}
