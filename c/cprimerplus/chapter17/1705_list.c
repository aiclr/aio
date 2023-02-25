/**
 * 实现 1703_list.h 接口
 * 支持链表操作的函数
 */
#include <stdio.h>
#include <stdlib.h>
#include "1703_list.h"

static void CopyToNode(Item item,Node * pnode);
//把链表设置为空
void InitializeList(List * plist)
{
	*plist = NULL;
}
//链表是否为空
bool ListIsEmpty(const List * plist)
{
	if(*plist == NULL)
		return true;
	else
		return false;
}
//链表是否已满
bool ListIsFull(const List * plist)
{
	Node *pt;
	bool full;
	pt = (Node *)malloc(sizeof(Node));
	if(pt == NULL)
		full = true;
	else
		full = false;
	free(pt);
	return full;
}
//返回node的数量
unsigned int ListItemCount(const List * plist)
{
	unsigned int count = 0;
	Node * pnode = *plist;//设置链表的开始
	while(pnode != NULL)
	{
		++count;
		pnode = pnode->next;//设置下一个节点
	}
	return count;
}
/**
 * 创建储存item的节点,并将其添加至由plist指向的链表末尾(较慢的实现)
 */
bool AddItem(Item item,List * plist)
{
	Node * pnew;
	Node * scan = *plist;
	pnew = (Node *)malloc(sizeof(Node));
	if(pnew == NULL)
		return false;//失败时退出函数
	CopyToNode(item,pnew);
	pnew->next = NULL;
	if(scan == NULL)
		*plist = pnew;//空链表,把pnew放在链表的开头
	else
	{
		while(scan -> next != NULL)
			scan = scan->next;//找到链表的末尾
		//把pnew添加到链表的末尾
		scan->next = pnew;
	}
	return true;
}
//访问每个节点并执行pfun指向的函数
void Traverse(const List *plist,void(*pfun)(Item item))
{
	//设置链表的开始
	Node * pnode = *plist;
	while(pnode!=NULL)
	{
		//把函数应用于链表中的项
		(*pfun)(pnode->item);
		//前进到下一项
		pnode = pnode->next;
	}
}

/**
 * 释放由malloc()分配的内存
 * 设置链表指针为NULL
 */
void EmptyTheList(List * plist)
{
	Node * psave;
	while(*plist!=NULL)
	{
		//保存下一个节点的地址
		psave = (*plist)->next;
		//释放当前节点
		free(*plist);
		//前进至下一个节点
		*plist = psave;
	}
}

/**
 * 局部函数定义
 * 把一个Item 拷贝到Node中
 */
static void CopyToNode(Item item,Node * pnode)
{
	// 拷贝结构
	pnode->item = item;
}
