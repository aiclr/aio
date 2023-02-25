/**
 * #ifndef 
 * 判断后面的标识符是否是未定义的，常用于定义之前为定义的常量
 * 
 * 通常包含多个头文件时，其中的文件可能包含了相同的宏定义
 * #ifndef 指令 可以防止相同的宏被重复定义
 *
 * C标准头文件使用#ifndef避免重复包含
 * 标准头文件规则 文件名作为标识符、使用大写字母、用下划线字符代替文件中的点字符、用下划线字符做前缀或后缀
 * stdio.h
 * #ifndef _STDIO_H
 * #define _STDIO_H
 *
 * 避免与标准头文件中的宏冲突，请注意命名规范,不要以下划线开头
 *
 */
#ifndef NAMES_H_
#define NAMES_H_
//明示常量
#define SLEN 32
struct names_st
{
	char first[SLEN];
	char last[SLEN];
};
typedef struct names_st names;
void get_names(names*);
void show_names(const names*);
char * s_gets(char*,int);
#endif

