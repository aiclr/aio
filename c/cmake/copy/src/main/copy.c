#include <stdio.h>
/**
 * 提供exit() 原型 关闭所有打开的文件并结束程序
 * 0或EXIT_SUCCESS 表明成功结束程序
 * EXIT_FAILURE 表明结束程序失败
 */
#include <stdlib.h>
#include "file/file.h"
#include "progress/progress.h"

/**
 * cp xxx /home/leo/xxx
 * 显示 进度条
 * ./a.out /home/leo/samba/downloads/ideaIU-2021.2.tar.gz /home/leo/tmp.tar.gz
 */
int main(int argc, char **argv)
{

  if (argc != 3)
  {
    perror("please use \"cp xxx /home/leo/\"");
    exit(EXIT_FAILURE);
  }

  FILE *IFP = open_file(argv[1], "rb");
  FILE *OFP = open_file(argv[2], "wb");
  long filesize = get_file_size(IFP, argv[1]);
  copy(IFP, OFP, filesize, status_show);
  close_file(OFP, argv[1]);
  close_file(IFP, argv[2]);
  return 0;
}