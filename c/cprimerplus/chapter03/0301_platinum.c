/**
 * 0301_platinum.c your weight in platinum
 */
#include <stdio.h>
int main(void)
{
  float weight;/* 你的体重 */
  float value;/* 相等重量的白金价值 */
  printf("Are you worth your weight in paltinum?\n");
  printf("Let's check it out.\n");
  printf("Please enter your weight in pounds: ");
  scanf("%f", &weight);/* 获取用户的输入 */
  /**
   * 假设白金的价格是每盎司$1700
   * 16 * 28.350 / 31.104 = 14.5833 用于把英镑 常衡盎司转换为金衡盎司
   * 欧美日常使用的度量衡单位是常衡盎司 avoridupois ounce，英制计量单位，做重量单位时也称为英两
   * 欧美黄金市场上使用的黄金交易计量单位是金衡盎司 tory ounce
   * 国际黄金市场上的报价，其单位“盎司”都指的是黄金盎司。
   * 1常衡盎司=28.350克
   * 1金衡盎司=31.104克
   * 16常衡盎司=1磅
  */
  value = 1700.0 * weight * 16 * 28.35 / 31.104 ;
  printf("your weight in paltinum is worth $%.2f.\n", value);
  printf("You are easily worth that! If platinum prices drop,\n");
  printf("eat more to maintain your value.\n");
  return 0;
}
