/**
 * 可变参数 stdarg.h
 * 使用步骤
 * 1. 提供一个使用省略号的函数原型
 * 2. 在函数定义中创建一个va_list类型的变量
 * 3. 用宏把该变量初始化为一个参数列表
 * 4. 用宏访问参数列表
 * 5. 用宏完成清理工作
 * 示例
 * void f1(int n,...); //有效
 * int f2(const char *s,int k,...);//有效
 * char f3(char c1,...,char c2);//无效,省略号不在最后
 * double f3(...);//无效,没有形参
 *
 * 最右边的形参(即省略号的前一个形参)起着特素的作用,标准中用parmN 这个术语来描述该形参
 * 上面例子中
 * f1()中 parmN 为n,
 * f2()中 parmN 为k
 * 传递给该形参的实际参数是省略号部分代表的参数数量
 * 例如
 * 	f1(2,200,400);//2个额外的参数
 * 	f1(4,13,117,18,23);//4个额外的参数
 * 声明在stdarg.h 中的va_list类型代表一种用于储存形参对应的形参列表中省略号部分的数据对象
 * 变参函数的定义如下
 * double sum(int lim,...)
 * { va_list ap;//声明一个储存参数的对象
 * 该例中 lim 是parmN 形参,它表明变参列表中参数的数量
 * 该函数将使用定义在 stdarg.h 中的 va_start() 宏,把参数列表拷贝到 va_list类型的变量中
 * 该宏有两个参数:va_list类型的变量和parmN形参
 * 调用方式 va_start(ap,lim);//把ap初始化为参数列表
 *
 * 访问参数列表的内容,使用另一个宏 va_arg(),该宏接受两个参数
 * 一个va_list类型的变量和一个类型名
 * 第一次调用va_arg()时,它返回参数列表的第一项
 * 第二次调用时返回第二项,以此类推
 * 表示类型的参数指定了返回值的类型
 * 如果参数列表中的第一个参数是double类型
 * 第二个参数是int类型
 * double tic;
 * int toc;
 * ...
 * tic = va_arg(ap,double);//检索第一个参数
 * toc = va_arg(ap,int);//检索第二个参数
 * 注意传入的参数类型必须与宏参数的类型相匹配
 * 如果第一个参数是10.0,上面 tic那行代码可以正常工作
 * 如果参数是10,这行代码可能会出错
 * 这里不会像赋值那样把double类型自动转换成int 类型
 * 最后,要使用va_end(ap);//清理工作
 *
 * 调用va_end(ap)后,只有用va_start重新初始化ap后,才能使用变量ap
 * 因为va_arg()不提供退回之前参数的方法,所以有必要保存 va_list类型变量的副本
 * c99新增一个va_copy() 宏处理用于保存va_list.
 * 该宏接受两个va_list类型的变量作为参数,把第二个参数拷贝给第一个参数
 * va_list ap;
 * va_list apcopy;
 * double 
 * double tic;
 * int toc;
 * ...
 * va_start(ap,lim);//把ap初始化为一个参数列表
 * va_copy(apcopy,ap);//把apcopy作为ap的副本
 * tic = va_arg(ap,double);//检索第一个参数
 * toc = va_arg(ap,int);//检索第二个参数
 * 此时即使删除了ap,也可以从apcopy中检索两个参数
 */
#include <stdio.h>
#include <stdarg.h>
double sum(int,...);
int main(void)
{
	double s,t;
	s = sum(3,1.1,2.5,13.3);
	t = sum(6,1.1,2.1,13.1,4.1,5.1,6.1);
	printf("return value for sum(3,1.1,2.5,13.3): %g\n",s);
	printf("return value for sum(6,1.1,2.1,13.1,4.1,5.1,6.1): %g\n",t);
	return 0;
}
double sum(int lim,...)
{
	va_list ap;//声明一个对象储存参数
	double tot=0;
	int i;
	va_start(ap,lim);//把ap初始化为参数列表
	for(i=0;i<lim;i++)
		tot += va_arg(ap,double);//访问参数列表中的每一项
	va_end(ap);//清理工作
	return tot;
}
