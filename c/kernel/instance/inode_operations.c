#include <linux/init.h>
/**
 * file_operations的open与release接口的第一个参数是inode结构，
 * 该结构被内核用来表示一个文件节点，也就是一个具体的文件或目录。
 * 文件节点的操作结构定义如下
 */
struct inode_operations
{
  struct dentry *(*lookup)(struct inode *, struct dentry *, unsigned int);
  const char *(*get_link)(struct dentry *, struct inode *, struct delayed_call *);
  int (*permission)(struct inode *, int);
  struct posix_acl *(*get_acl)(struct inode *, int);
  int (*readlink)(struct dentry *, char __user *, int);
  int (*create)(struct inode *, struct dentry *, umode_t, bool);                   // 创建
  int (*link)(struct dentry *, struct inode *, struct dentry *);                   // 硬链接
  int (*unlink)(struct inode *, struct dentry *);                                  // 取消链接
  int (*symlink)(struct inode *, struct dentry *, const char *);                   // 软链接
  int (*mkdir)(struct inode *, struct dentry *, umode_t);                          // 创建目录
  int (*rmdir)(struct inode *, struct dentry *);                                   // 删除目录
  int (*mknod)(struct inode *, struct dentry *, umode_t, dev_t);                   // 创建节点
  int (*rename)(struct inode *, struct dentry *, struct inode *, struct dentry *); // 重命名
  int (*rename2)(struct inode *, struct dentry *, struct inode *, struct dentry *, unsigned int);
  int (*setattr)(struct dentry *, struct iattr *);
  int (*getattr)(struct vfsmount *mnt, struct dentry *, struct kstat *);
  int (*setxattr)(struct dentry *, const char *, const void *, size_t, int);
  ssize_t (*getxattr)(struct dentry *, const char *, void *, size_t);
  ssize_t (*listxattr)(struct dentry *, char *, size_t);
  int (*removexattr)(struct dentry *, const char *);
  int (*fiemap)(struct inode *, struct fiemap_extent_info *, u64 start, u64 len);
  int (*update_time)(struct inode *, struct timespec *, int); // 更新时间
  int (*atomic_open)(struct inode *, struct dentry *, struct file *, unsigned open_flag, umode_t_create_mod, int *opened);
  int (*tmpfile)(struct inode *, struct dentry *, umode_t);
  int (*set_acl)(struct inode *, struct posix_acl *, int);
} ____cacheline_aligne