/**
 * 1706_queue.h 实现
 */
#include <stdio.h>
#include <stdlib.h>
#include "1706_queue.h"

//局部函数
static void CopyToNode(Item item,Node * pn);
static void CopyToItem(Node * pn,Item *pi);

void InitializeQueue(Queue *pq)
{
    pq->front = pq->rear = NULL;
    pq->items = 0;
}

bool QueueIsFull(const Queue *pq)
{
    return pq->items == MAXQUEUE;
}

bool QueueIsEmpty(const Queue *pq)
{
    return pq->items == 0;
}

int QueueItemCount(const Queue *pq)
{
    return pq->items;
}
//入队
bool EnQueue(Item item,Queue * pq)
{
    Node * pnew;
    if(QueueIsFull(pq))
        return false;
    pnew = (Node *) malloc(sizeof(Node));
    if(pnew == NULL)
    {
        fprintf(stderr,"Unable to allocate memory!\n");
        exit(1);
    }
    //将待添加的item,放到临时创建的新节点pnew
    CopyToNode(item,pnew);
    pnew->next = NULL;
    if(QueueIsEmpty(pq))
        pq->front = pnew;//空队列队首指向新添加的项
    else
        pq->rear->next=pnew;//非空队列 先添加的项放在队尾
    pq->rear = pnew; //设置新的队尾
    pq->items++;//队列总项数加一
    return true;
}
//出队
bool DeQueue(Item * pitem,Queue * pq)
{
    Node * pt;
    if(QueueIsEmpty(pq))
        return false;
    // 出队的 Item 被复制到 pitem
    CopyToItem(pq->front,pitem);
    pt = pq->front;
    pq->front = pq->front->next;
    free(pt);
    pq->items--;
    if(pq->items == 0)
        pq->rear = NULL;
    return true;
}

void EmptyTheQueue(Queue * pq)
{
    Item dummy;
    while(!QueueIsEmpty(pq))
        DeQueue(&dummy,pq);
}

static void CopyToNode(Item item,Node * pn)
{
    pn->item = item;
}

static void CopyToItem(Node * pn, Item * pi)
{
  *pi = pn->item;
}

