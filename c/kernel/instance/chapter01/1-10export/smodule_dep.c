#include <linux/module.h>

int function_of_dep(void)
{
  printk("function_of_dep\n");
  return 0;
}
EXPORT_SYMBOL(function_of_dep);/* 导出函数 */

MODULE_DESCRIPTION("simple module depends");
MODULE_LICENSE("GPL");