/** 
 * randbin.c -- 用二进制I/O进行随即访问
 */
#include <stdio.h>
#include <stdlib.h>
#define ARSIZE 1000
int main(void)
{
	double numbers[ARSIZE];
	double value;
	const char * file="numbers.dat";
	int i;
	long pos;
	FILE * iofile;
	//创建一组double类型值
	for(i=0;i<ARSIZE;i++)
		numbers[i]=100.0*i+1.0/(i+1);
	//尝试打开文件 wb: w 写模式打开，没有则新建。b二进制形式
	if((iofile=fopen(file,"wb"))==NULL)
	{
		fprintf(stderr,"Could not open %s for output.\n",file);
		exit(EXIT_FAILURE);
	}
	//以二进制格式把数组写入文件
	fwrite(numbers,sizeof(double),ARSIZE,iofile);
	fclose(iofile);
	if((iofile=fopen(file,"rb"))==NULL)
	{
		fprintf(stderr,"Could not open %s for random access.\n",file);
		exit(EXIT_FAILURE);
	}
	//从文件中读取选定的内容
	printf("Enter an index in the range 0-%d.\n",ARSIZE-1);
	while(scanf("%d",&i)==1 && i>=0 && i< ARSIZE)
	{
		pos=(long)i*sizeof(double);//计算偏移量，从零开始
		//fseek()函数，在fopen()打开的文件中直接移动到任意字节处，类似数组移动到指定下标处
		fseek(iofile,pos,SEEK_SET);//定位到此处
		//从fseek定位处开始读取1个double所占字节大小的（8byte 32bit）数据到放到&value处
		fread(&value,sizeof(double),1,iofile);
		printf("The value there is %f.\n",value);
		printf("Next index (out of range to quit):\n");
	}
	fclose(iofile);
	puts("Bye!");
	return 0;
}

