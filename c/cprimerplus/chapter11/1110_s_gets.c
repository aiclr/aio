/**
 * s_gets.c -- C11 新增gets_s()可选函数 与fgets()类似,fgets()更好用 
 *  输入行多余字符会被留在缓冲区，成为下一次读取语句的输入
 *  gets_s() 只从标准输入中读取数据
 *  gets_s() 读到换行符会丢弃
 *  gets_s() 如果gets_s()读到最大字符数，都没有读到换行符
 *  		首先把目标数组中的首字符设置为空字符
 *  		读取并丢弃随后的输入直至读到换行符或EOF,
 *  		然后返回空指针null pointer
 *  		接着调用依赖实现的处理函数可能会中止或退出程序
 *  gets_s() 输入行未超过最大字符数 gets_s()和gets() 几乎一样
 *  
 *  对比fgets() gets() gets_s()
 *  如果目标存储区装得下输入行，3个函数都没问题，fgets()函数会保存输入末尾的换行符最为字符串的一部分
 *  输入行太长：gets()不安全，会擦写现有数据，存在安全隐患
 *  		gets_s()安全，但是会导致程序中止或退出，需要编写特殊的处理函数。gets_s()函数会丢弃该输入行的其余字符如下代码
 *  		fgets() 比较灵活如代码1109
 *
 *  当输入与预期不符时，fgets()通常是最佳选择
 *
 * */
char *s_gets(char *st,int n)
{
	char *ret_val;
	int i=0;
	ret_val = fgets(st,n,stdin);
	if(ret_val)
	{
		while(st[i] != '\n' && st[i] !='\0')
			i++;
		if(st[i] ='\n')
			st[i]='\0';
		else
			while(getchar()!='\n')
				continue;
	}
	return ret_val;
}
