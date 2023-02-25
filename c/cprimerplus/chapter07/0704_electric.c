/* electric.c -- 计算电费 */
#include <stdio.h>
#define RATE1 0.13230 //首次使用360kwh的费率
#define RATE2 0.15040 //接着再使用108kwh的费率
#define RATE3 0.30025 //接着再使用252kwh的费率
#define RATE4 0.34025 //使用超过720kwh的费率
#define BREAK1 360.0 //费率的第一个分界点
#define BREAK2 468.0 //费率的第二个分界点
#define BREAK3 720.0 //费率的第三个分界点
#define BASE1 (RATE1 * BREAK1) 
//使用360kwh的费用
#define BASE2 (BASE1 + (RATE2 * (BREAK2-BREAK1)))
//使用468kwh的费用
#define BASE3 (BASE1 + BASE2 + (RATE3*(BREAK3-BREAK2)))
//使用720kwh的费用
int main(void)
{
	double kwh;
	double bill;
	printf("Please enter the kwh used.\n");
	scanf("%lf",&kwh);
	if(kwh <= BREAK1)
		bill = RATE1 * kwh;
	else if(kwh <= BREAK2)
		bill = BASE1 +(RATE2 * (kwh-BREAK1));
	else if(kwh <= BREAK3)
		bill = BASE2 +(RATE3*(kwh-BREAK2));
	else 
		bill = BASE3 + (RATE4*(kwh-BREAK3));
	printf("The charge for %.1f kwh is $%1.2f.\n",kwh,bill);
	return 0;
}
