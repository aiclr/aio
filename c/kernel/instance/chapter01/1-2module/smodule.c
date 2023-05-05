#include <linux/init.h>
#include <linux/module.h>
#include <linux/kernel.h>

static int itype=0;
module_param(itype,int,0);

static bool btype=0;
module_param(btype,bool,0);

static unsigned char ctype=0;
module_param(ctype,byte,0);

static char *stype=0;
module_param(stype,charp,0);
//模块初始化
static int __init demo_module_init(void)
{
  printk("simple module init\n");
  printk("itype=%d\n",itype);
  printk("btype=%d\n",btype);
  printk("ctype=%d\n",ctype);
  printk("stype=%s\n",stype);
  return 0;
}
//模块卸载
static void __exit demo_module_exit(void)
{
  printk("simple module exit\n");
}
module_init(demo_module_init);
module_exit(demo_module_exit);
MODULE_DESCRIPTION("param module");
MODULE_LICENSE("GPL");