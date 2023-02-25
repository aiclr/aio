/**
 * 1709_mall.c 使用 1709_queue.h 队列接口
 * 和 1709_queue.c 一起编译
 * gcc 1709_queue.c 1709_mall.c -o 1709_mall.out
 */
 #include <stdio.h>
 #include <stdlib.h> //提供 rand() 和 srand() 的原型
 #include <time.h> // 提供 time() 的原型 返回一个值，即格林尼治时间1970年1月1日00:00:00到当前时刻的时长，时长单位是秒
 #include "1709_queue.h"
 #define MIN_PER_HR 60.0

 bool newcustomer(double x); //是否有新顾客到来
 Item customertime(long when); // 设置顾客参数

 int main(void)
 {
     Queue line;
     Item temp;// 新的顾客数据
     int hours;//模拟的小时数
     int perhour;//每小时平均多少位顾客
     long cycle,cyclelimit;//循环计数器,计数器上限
     long turnaways = 0;//因队列已满被拒的顾客数量
     long customers = 0;//加入队列的顾客数量
     long served = 0;//在模拟期间咨询过 Sigmund 的顾客数量
     long sum_line = 0;//累计的队列总长
     int wait_time = 0;//从当前到 Sigmund 空闲所需的时间
     double min_per_cust;//顾客到来的平均时间
     long line_wait = 0;//队列累计的等待时间

     InitializeQueue(&line);//初始化队列

     //srand()用来设置rand()产生随机数时的随机数种子。参数seed是整数，通常可以利用time(0)或geypid(0)的返回值作为seed
     srand((unsigned int) time(0));//rand() 随机初始化

     puts("Case Study: Sigmund Lander's Advice Booth");
     puts("Enter the number of simulation hours:");
     scanf("%d",&hours);
     cyclelimit = MIN_PER_HR * hours; //模拟分钟数
     puts("Enter the average number of customers per hour:");
     scanf("%d",&perhour);//平均一小时到达多少位客户
     min_per_cust = MIN_PER_HR / perhour; //每个顾客到来的平均时间
     /**
      * cycle 是时刻 (类似数轴 ,单位一分钟,一小时则循环60次,所以一小时最多60顾客)
      * 每到该时刻处理一下下面的业务逻辑
      * 直到测试的时间段结束
      */
     for(cycle = 0; cycle < cyclelimit; cycle++)
     {
         if(newcustomer(min_per_cust))//利用随机数以及平均一分钟到到达的顾客数确定该 时刻 是否有新顾客
         {
             if(QueueIsFull(&line))//队满 劝退并+1
                 turnaways++;
             else
             {
                 customers++;//顾客总数 +1
                 temp = customertime(cycle);//新顾客
                 EnQueue(temp,&line);
             }
         }
         /**
          * 顾客等待时间 第一位顾客不需要等待
          * 上面已经将新顾客加入队列
          * 此处由于不需要等待,直接出队,顾客开始咨询
          * 将等待时间设置为咨询时间
          * 顾客排队等待时间 = 当前时刻 - 顾客到达时刻
          * 咨询顾客数+1
          */
         if(wait_time <=0 && !QueueIsEmpty(&line))
         {
             DeQueue(&temp,&line);
             wait_time = temp.processtime;
             line_wait += cycle - temp.arrive;
             served++;
         }
         if(wait_time > 0)
         {
             wait_time--;
         }
         /**
          * 当前时刻的队列长度
          */
         sum_line += QueueItemCount(&line);
     }
     if(customers > 0)
     {
         printf("customers accepted: %ld\n",customers);
         printf("  customers served: %ld\n",served);
         printf("       turnaways: %ld\n",turnaways);
         printf("average queue size: %.2f\n",(double)sum_line/cyclelimit);
         printf(  "average wait time: %.2f minutes\n",(double)line_wait/served);
     }
     else
         puts("No customers!");
     EmptyTheQueue(&line);
     puts("Bye!");
     return 0;
 }
/**
 * x 是顾客到来的平均时间(单位:分钟)
 * 如果一分钟内有顾客到来,则返回true
 */
 bool newcustomer(double x)
 {
     /**
      * 在调用rand()函数之前，可以使用srand()函数设置随机数种子
      * 如果没有设置随机数种子，rand()函数在调用时，自动设计随机数种子为1。随机种子相同，每次产生的随机数也会相同。
      * 使用rand()函数产生1-100以内的随机整数：int number1 = rand() % 100+1;
      * rand()产生一个0到0x7ffff即0到32767之间的随机数
      * RAND_MAX即为0x7ffff。
      * 所以rand()/RAND_MAX可表示为在[0,1]区间内的随机数
      * rand()/(RAND_MAX+1)可表示为在[0,1)区间内的随机数
      */
     if(rand() * x / RAND_MAX < 1)
     {
         return true;
     }
     else return false;
 }

/**
 * when 是顾客到来的时间
 * 该函数返回一个Item结构,该顾客到达的时间设置为when
 * 咨询时间设置为1~3的随即值
 */
 Item customertime(long when)
 {
     Item cust;
     cust.processtime = rand() % 3 + 1;
     cust.arrive = when;
     return cust;
 }