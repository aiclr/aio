#include "apue.h"

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