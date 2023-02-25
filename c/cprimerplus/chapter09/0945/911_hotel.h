/* hotel.h -- 符号常量和hotel.c中所有函数的原型 */
#define QUIT 5
#define HOTEL1 180.00
#define HOTEL2 225.00
#define HOTEL3 255.00
#define HOTEL4 355.00
#define DISCOUNT 0.95
#define STARS "*********"
int menu(void);//显示选择列表
int getnights(void);//返回预定天数
void showprice(double,int);//显示结果
