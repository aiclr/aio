#include <stdio.h>
/**
 * 提供exit() 原型 关闭所有打开的文件并结束程序
 * 0或EXIT_SUCCESS 表明成功结束程序
 * EXIT_FAILURE 表明结束程序失败
 */
#include <stdlib.h>

/**
 * cp xxx /home/leo/xxx
 * 显示 进度条
 * ./a.out /home/leo/samba/downloads/ideaIU-2021.2.tar.gz /home/leo/tmp.tar.gz
 */

static void status_show(long total,long deal);
static void copy(FILE *inputfile, FILE *outputfile, long filesize, void (*show)(long total,long deal));

static long get_file_size(FILE *file, const char *filename);
static FILE * open_file(const char *filename, const char *modes);
static void close_file(FILE *file, const char *filename);
static void set_buffer(FILE *file, char *bufer, int __modes, size_t buffersize);

static long get_file_size(FILE *file, const char *filename)
{
  // 定位到文件末尾，file 指针地址偏移到EOF
  if (fseek(file, 0L, SEEK_END) == -1)
  {
    fprintf(stderr, "fseek(%s,0L,SEEK_END) failed.\n", filename);
    exit(EXIT_FAILURE);
  }
  /**
   * 获取文件末尾到文件开始处的字节数
   *
   * ftell() 返回的是 file 当前位置
   * 最初的实现为：返回距文件开始处的字节数，文件第一个字节到文件开始出的距离是0，适用于以二进制模式打开的文件
   * 以文件模式打开文件的情况不同
   */
  long size = ftell(file);
  /* 复位------定位到文件开头，fp指针地址偏移到起始位置 */
  if (fseek(file, 0L, SEEK_SET) == -1)
  {
    fprintf(stderr, "fseek(%s,0L,SEEK_SET) failed.\n", filename);
    exit(EXIT_FAILURE);
  }
  return size;
}

static FILE *open_file(const char *filename, const char *modes)
{
  FILE *file = NULL;
  if ((file = fopen(filename, modes)) == NULL)
  {
    fprintf(stderr, "Couldn't open %s.\n", filename);
    exit(EXIT_FAILURE);
  }
  return file;
}

static void close_file(FILE *file, const char *filename)
{
  if (fclose(file) != 0)
  {
    fprintf(stderr, "Error in closing file %s\n", filename);
    exit(EXIT_FAILURE);
  }
}

static void set_buffer(FILE *file, char *bufer, int __modes, size_t buffersize)
{
  if (setvbuf(file, bufer, __modes, buffersize) != 0)
  {
    fputs("Can't create buffer\n", stderr);
    exit(EXIT_FAILURE);
  }
}

static void copy(FILE *inputfile, FILE *outputfile, long filesize, void (*show)(long total,long deal))
{
  size_t buffersize = 1 << 12; // 4k
  set_buffer(inputfile, NULL, _IOFBF, buffersize);
  set_buffer(outputfile, NULL, _IOFBF, buffersize);

  size_t readsize;
  long handlesize = 0L;
  char *piece = (char *)calloc(buffersize, sizeof(char));
  while ((readsize = fread(piece, sizeof(char), buffersize, inputfile)) > 0)
  {
    fwrite(piece, sizeof(char), readsize, outputfile);
    handlesize = ftell(inputfile);
    printf("\b\b\b\b%3d%%", (int)(((float)handlesize/filesize)*100));
  }
  puts("\nBye!");
  free(piece);
}

static void status_show(long total,long deal)
{
  printf("\b\b\b\b%3d%%", (deal/total)*100);
}

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