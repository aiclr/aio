/** running.c -- A useful program for runners */
#include <stdio.h>
const int S_PER_M = 60; //1 minute have 60 seconds
const int S_PER_H = 3600; //1 hour have 3600 seconds
const double M_PER_K = 0.62137; //1 km = 0.62137 英里
int main(void)
{
	double distk,distm; //跑过的距离 分别以公里和英里为单位
	double rate; //平均速度 英里/小时
	int min,sec;//跑步用时 分钟和秒为单位
	int time;//跑步用时单位秒
	double mtime;//跑1英里需要的时间，单位秒
	int mmin,msec;//跑1英里需要的时间，以分钟和秒为单位
	printf("This program converts your time for a metric race\n");
	printf("to a time for running a mile and to your average\n");
	printf("speed in miles per hour.\n");
	printf("Please enter,in kilometers, the distance run.\n");
	scanf("%lf",&distk);// %lf 表示读取一个double类型的值
	printf("Next enter the time in minutes and seconds.\n");
	printf("Begin by entering the minutes.\n");
	scanf("%d",&min);
	printf("Now enter the seconds\n");
	scanf("%d",&sec);
	time = S_PER_M * min +sec;
	distm = distk * M_PER_K;
	rate = distm / time * S_PER_H;
	mtime = (double)time / distm;//时间/距离 跑一英里所需时间爱你
	mmin = (int)mtime / S_PER_M;//秒/60 = 分钟 
	msec =(int)mtime % S_PER_M;//秒%60 = 不足一分钟的秒数
	printf("You ran %.2fKM (%.2f miles) in %d min,%d sec.\n",distk,distm,min,sec);
	printf("That pace corresponds to running a mile in %d min, ",mmin);
	printf("%d sec.\nYour average speed was %.2f mph.\n",msec,rate);
	return 0;
}
