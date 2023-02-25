/** 
 * usehotel.c -- 房间费率程序 
 * 与910一起编译 
 */
#include <stdio.h>
#include "911_hotel.h" //双引号表明头文件位于当前目录中
int main(void)
{
	int nights;
	double hotel_rate;
	int code;
	while((code = menu())!=QUIT)
	{
		switch(code)
		{
			case 1:
				hotel_rate = HOTEL1;
				break;
			case 2:
				hotel_rate = HOTEL2;
				break;
			case 3:
				hotel_rate = HOTEL3;
				break;
			case 4:
				hotel_rate = HOTEL4;
				break;
			default:
				hotel_rate = 0.0;
				printf("Oops!\n");
				break;
		}
		nights = getnights();
		showprice(hotel_rate,nights);
	}
	printf("Thank you and goodbye.\n");
	return 0;
}
