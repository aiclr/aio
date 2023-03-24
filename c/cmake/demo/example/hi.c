#include "shared/swap.h"
#include "static/max.h"
#include <stdio.h>
int main(){
  printf("我是谁，我从哪里来，我要到那里去\n");
  int a=1;
  int b=2;
  printf("a=%d,b=%d\n",a,b);
  swap(&a,&b);
  printf("a=%d,b=%d\n",a,b);
  printf("%d与%d相比,%d更大\n",a,b,max(a,b));
}
