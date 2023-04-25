#include <stdlib.h>
#include "file.h"

long get_file_size(FILE *file, const char *filename)
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

FILE *open_file(const char *filename, const char *modes)
{
  FILE *file = NULL;
  if ((file = fopen(filename, modes)) == NULL)
  {
    fprintf(stderr, "Couldn't open %s.\n", filename);
    exit(EXIT_FAILURE);
  }
  return file;
}

void close_file(FILE *file, const char *filename)
{
  if (fclose(file) != 0)
  {
    fprintf(stderr, "Error in closing file %s\n", filename);
    exit(EXIT_FAILURE);
  }
}

void set_buffer(FILE *file, char *bufer, int __modes, size_t buffersize)
{
  if (setvbuf(file, bufer, __modes, buffersize) != 0)
  {
    fputs("Can't create buffer\n", stderr);
    exit(EXIT_FAILURE);
  }
}

void copy(FILE *inputfile, FILE *outputfile, long filesize, void (*show)(long total,long deal))
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
