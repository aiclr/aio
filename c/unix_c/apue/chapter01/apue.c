#include "apue.h"

/**
 * man 3 va_list
 * man 3 va_start
 * man 3 va_end
 * ISO C variable arguments
 */
#include <stdarg.h>

/**
 * man 3 errno
 * for definition of errno
 */
#include <errno.h>

static void err_doit(int errnoflag,int error,const char *fmt,va_list ap);

/**
 * Nonfatal error related to a system call
 * print a messagge and return
 */
void err_ret(const char * fmt,...)
{
	va_list ap;
	va_start(ap,fmt);
	err_doit(1,errno,fmt,ap);
	va_end(ap);
}

void err_quit(const char * fmt,...)
{
  va_list ap;
  va_start(ap,fmt);
  err_doit(0,0,fmt,ap);
  va_end(ap);
  exit(1);
}

void err_sys(const char * fmt,...)
{
  va_list ap;
  va_start(ap,fmt);
  err_doit(1,errno,fmt,ap);
  va_end(ap);
  exit(1);
}

/**
 * Print a message and return to caller
 * Caller specifies "errnoflag".
 */
static void err_doit(int errnoflag,int error,const char *fmt,va_list ap)
{
  char buf[MAXLINE];
  vsnprintf(buf,MAXLINE-1,fmt,ap);
  if(errnoflag)
  {
    snprintf(buf+strlen(buf),MAXLINE-strlen(buf)-1,": %s",strerror(error));
  }
  strcat(buf,"\n");
  fflush(stdout);/* in case stdout and stderr are the same */
  fputs(buf,stderr);
  fflush(NULL);/* flushes all stdio output stream */
}
