/**
 * 使用抽象数据类型(ADT)风格的链表
 * 与 1703_list.c 一起编译
 * gcc 1704_films3.c 1703_list.c -o 1704_films3.out
 */
#include <stdio.h>
#include <string.h>
#include <stdlib.h> // 提供exit() 的原型
#include "1703_list.h"//定义 List Item

void showmovies(Item item);
char * s_gets(char *st,int n);

int main(void)
{
	List movies;
	Item temp;
	InitializeList(&movies);
	if(ListIsFull(&movies))
	{
		fprintf(stderr,"No memory available! Bye!\n");
		exit(1);
	}
	puts("Enter first movie title:");
	while(s_gets(temp.title,TSIZE) != NULL && temp.title[0] != '\0')
	{
		puts("Enter your rating <0-10>:");
		scanf("%d",&temp.rating);
		while(getchar() != '\n')
			continue;
		if(AddItem(temp,&movies) == false)
		{
			fprintf(stderr,"Problem allocating memory\n");
			break;
		}
		if(ListIsFull(&movies))
		{
			puts("The list is now full.");
			break;
		}
		puts("Enter next movie title (empty line to stop):");
	}
	if(ListIsEmpty(&movies))
		printf("No data entered. ");
	else
	{
		printf("Here is the movie list:\n");
		Traverse(&movies,showmovies);
	}
	printf("You entered %d movies.\n", ListItemCount(&movies));
	EmptyTheList(&movies);
	printf("Bye!\n");
	return 0;
}

void showmovies(Item item)
{
	printf("Movie: %s Rating: %d\n",item.title,item.rating);
}

char * s_gets(char * st,int n)
{
	char * ret_val;
	char * find;
	ret_val=fgets(st,n,stdin);
	if(ret_val)
	{
		find = strchr(st,'\n');
		if(find)
		{
			*find = '\0';
		}
		else
			while(getchar() != '\n')
				continue;
	}
	return ret_val;
}
