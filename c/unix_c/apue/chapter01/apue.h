#ifndef _CHAPTER01_H
#define _CHAPTER01_H

/**
 * man 3 va_list
 * man 3 va_start
 * man 3 va_end
 */
#include <stdarg.h>
/**
 * man 3 errno
 */
#include <errno.h>




/**
 * man NULL
 */
#include <stddef.h>
/**
 * man 3 fflush
 * man 3 stdout
 * man 3 stderr
 * man 3 fputs
 * man 3 vsnprintf
 * man 3 snprintf
 * man 3 printf
 */
#include <stdio.h>

/**
 * man 3 strlen
 * man 3 strcat
 * man 3 strerror
 */
#include <string.h>

/**
 * man 3 exit
 */
#include <stdlib.h>

#define MAXLINE 4096 /* max line length */


static void err_doit(int,int,const char *,va_list); 
void err_quit(const char*,...) __attribute__((noreturn));
void err_sys(const char*,...) __attribute__((noreturn));

#endif /* _CHAPTER01_H */
