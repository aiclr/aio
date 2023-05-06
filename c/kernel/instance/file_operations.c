#include <linux/init.h>

/**
 * 对于字符设备驱动程序，最核心的就是file_operations结构，
 * 该结构实际上是提供给虚拟文件系统 VFS 的文件接口，每一个成员函数一般都对应一个系统调用。
 * 用户进程利用系统调用对设备文件进行诸如读和写等操作时，
 * 系统调用通过设备文件的主设备号找到相应的设备驱动程序，并调用相应的设备驱动程序，
 * 并调用相应的驱动程序函数
 * file_operations结构定义如下
 */
struct file_operations
{
  struct module *owner;                         // 模块所有者
  loff_t (*llseek)(struct file *, loff_t, int); // 寻找文件读写位置
  /**
   * /usr/src/linux/include/linux/compiler_types.h
   * # define __user		__attribute__((noderef, address_space(__user)))
   *
   * __attribute__是gnu c编译器的一个功能，它用来让开发者使用此功能给所声明的函数或者变量附加一个属性，
   * 以方便编译器进行错误检查，其实就是一个内核检查器
   *
   * __user修饰符 表示用户地址空间
   * This annotation is a form of documentation,
   * noting that a pointer is a user-space address that cannot be directly dereferenced.
   * For normal compilation, __user has no effect,
   * but it can be used by external checking software to find misuse of user-space addresses
   * 此注释是一种文档形式，
   * 指出指针是不能直接取消引用的用户空间地址。
   * 对于正常编译，__user没有任何作用，
   * 但外部检查软件可以使用它来发现用户空间地址的滥用
   */
  ssize_t (*read)(struct file *, char __user *, size_t, loff_t *);        // 读
  ssize_t (*write)(struct file *, const char __user *, size_t, loff_t *); // 写
  ssize_t (*read_iter)(struct kiocb *, struct iov_iter *);                // 多缓冲读
  ssize_t (*write_iter)(struct kiocb *, struct iov_iter *);               // 多缓冲写
  int (*iterate)(struct file *, struct dir_context *);
  unsigned int (*poll)(struct file *, struct poll_table_struct *);   // 查询可读性
  long (*unlocked_ioctl)(struct fie *, unsigned int, unsigned long); // 未加锁的控制接口
  // 在64位系统上处理32为的ioctl调用
  long (*compat_ioctl)(struct file *, unsigned int, unsigned long);
  int (*mmap)(struct file *, struct vm_area_struct *);       // 内存映射
  int (*open)(struct inode *, struct file *);                // 打开设备
  int (*flush)(struct file *, fl_owner_t id);                // 执行未完成的操作
  int (*release)(struct inode *, struct file *);             // 释放
  int (*fsync)(struct file *, loff_t, loff_t, int datasync); // 刷新待处理的数据
  int (*aio_fsync)(struct kiocb *, int datasync);            // 异步刷新待处理的数据
  int (*fasync)(int, struct file *, int);                    // 通知设备FASYNC标志发生变化
  int (*lock)(struct file *, int, struct file_lock *);       // 文件锁
  // 发送数据，一次一页
  ssize_t (*sendpage)(struct file *, struct page *, int, size_t, loff_t *, int);
  unsigned long (*get_unmapped_area)(struct file *, unsigned long, unsigned long, unsigned long, unsigned long); // 获取未映射区
  int (*check_flags)(int);                                                                                       // 检查传递给fcntl(fd,F_SETEL...)调用的标志
  int (*flock)(struct file *, int, struct file_lock *);                                                          // 另一种文件锁
  ssize_t (*splice_write)(struct pipe_inode_info *, struct file *, loff_t *, size_t, unsigned int);
  ssize_t (*splice_read)(struct file *, loff_t *, struct pipe_inode_info *, size_t, unsigned int);
  int (*setlease)(struct file *, long, struct file_lock **, void **);
  long (*fallocate)(struct file *file, int mode, loff_t offset, loff_t len);
  void (*show_fdinfo)(struct seq_file *m, struct file *f);
#ifndef CONFIG_MMU
  unsigned (*mmap_capabilities)(struct file *);
#endif
  ssize_t (*copy_file_range)(struct file *, loff_t, struct file *, loff_t, size_t, unsigned int);
  int (*clone_file_range)(struct file *, loff_t, struct file *, loff_t, u64);
  ssize_t (*dedupe_file_range)(struct file *, u64, u64, strict file *, u64);
}