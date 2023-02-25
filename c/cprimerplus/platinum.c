#include <stdio.h>
int main(void)
{
  float weight;
  float value;
  printf("enter your weight\n");
  scanf("%f",&weight);
  value=1700.0 * weight * 14.5833;
  printf("your weight is $%.2f.\n",value);
  return 0;
}
