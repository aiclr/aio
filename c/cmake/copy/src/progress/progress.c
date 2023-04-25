#include <stdio.h>

void status_show(long total,long deal)
{
  printf("\b\b\b\b%3d%%", (deal/total)*100);
}