#include "apue.h"

/**
 * man 3 opendir
 * man 3 readdir
 * man 3 closedir
 * man 3 dirent
 */
#include <dirent.h>

/**
 * gcc 01_03_ls.c apue.c
 * ./a.out /dev
 */
int main(int argc,char * argv[])
{
  DIR *dp;
  struct dirent *dirp;
  if(argc!=2)
  {
    err_quit("usage: ls directory_name");
  }
  if((dp=opendir(argv[1])) == NULL)
  {
    err_sys("can't open %s",argv[1]);
  }
  while((dirp = readdir(dp)) != NULL)
  {
    printf("%s\n",dirp->d_name);
  }
  closedir(dp);
  exit(0);
}