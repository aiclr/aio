/**
 * 队列ADT
 * 队列先进先出
 * queue first in first out
 */
#ifndef _QUEUE_H_
#define _QUEUE_H_
#include <stdbool.h>

// 定义 队列中的数据类型 此处为了简单使用int 可以使用定义的struct
typedef struct item
{
    long arrive;//一位顾客加入队列的时间
    int processtime;//该顾客咨询时花费的时间
} Item;

#define MAXQUEUE 10;

//定义节点
typedef struct node
{
	Item item;
	struct node * next;
} Node;
//定义队列
typedef struct queue
{
	Node * front;//指向队列首项的指针
	Node * rear;//指向队列尾项的指针
	int items;//队列中的项数
} Queue;

/**
 * 操作: 初始化队列
 * 前提条件: pq指向一个队列
 * 后置条件: 队列被初始化为空
 */
void InitializeQueue(Queue * pq);
/**
 * 操作: 检查队列是否已满
 * 前提条件: pq指向之前被初始化的队列
 * 后置条件: 如果队列已满则返回true,否则返回false
 */
bool QueueIsFull(const Queue * pq);
/**
 * 操作: 检查队列是否为空
 * 前提条件: pq指向之前被初始化的队列
 * 后置条件: 如果队列为空则返回true,否则返回false
 */
bool QueueIsEmpty(const Queue *pq);
/**
 * 操作: 确定队列中的项数
 * 前提条件: pq指向之前被初始化的队列
 * 后置条件: 返回队列中的项数
 */
int QueueItemCount(const Queue *pq);
/**
 * 操作: 在队列末尾添加项
 * 前提条件: pq指向之前被初始化的队列
 *           item 是要被添加在队尾的项
 * 后置条件: 如果队列不为空,item将被添加在队列的末尾,该函数返回true
 *           否则,队列不改变,该函数返回false
 */
bool EnQueue(Item item,Queue * pq);
/**
 * 操作:从队列的开头删除项 出队操作
 * 前提条件: pq指向之前被初始化的队列
 * 后置条件: 如果队列不为空,队列首端的item将被拷贝到 *pitem中
 *           并被删除,且函数返回true
 *           如果该操作使得队列为空,则重置队列为空
 *           如果队列在操作前为空,该函数返回false
 */
bool DeQueue(Item *pitem,Queue *pq);
/**
 * 操作: 清空队列
 * 前提条件: pq指向之前被初始化的队列
 * 后置条件: 队列被清空
 */
void EmptyTheQueue(Queue *pq);
#endif
