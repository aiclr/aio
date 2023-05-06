#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

extern int function_of_dep(void);

static int __init demo_module_init(void)
{
  printk("simple module init\n");
  function_of_dep();
  return 0;
}
static void demo_module_exit(void)
{
  printk("demo_module_exit\n");
}
/* 模块入口 */
module_init(demo_module_init);
module_exit(demo_module_exit);
MODULE_DESCRIPTION("simple module");
MODULE_LICENSE("GPL");