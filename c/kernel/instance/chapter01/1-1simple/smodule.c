#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

static int demo_module_init(void)
{
  /**
   * 模块运行在内核态
   * 不能使用用户态C库函数中的printf函数
   * 需要使用printk函数打印调试信息
   */
  printk("demo_module_init\n");
  return 0;
}
static void demo_module_exit(void)
{
  printk("demo_module_exit\n");
}
//模块入口
module_init(demo_module_init);
module_exit(demo_module_exit);
MODULE_DESCRIPTION("simple module");
MODULE_LICENSE("GPL");