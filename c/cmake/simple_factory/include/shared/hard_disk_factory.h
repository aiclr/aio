#ifndef HARD_DISK_FACTORY_H
#define HARD_DISK_FACTORY_H

#include "hard_disk.h"

enum HARD_DISK_SUPPLIER_E
{
    HARD_DISK_SUPPLIER_SEAGATE,
    HARD_DISK_SUPPLIER_TOSHIBA
};

struct HardDiskFactory
{
    struct HardDisk *(*Create)(struct HardDiskFactory *this, enum HARD_DISK_SUPPLIER_E supplier);
    void (*Destory)(struct HardDiskFactory *this, struct HardDisk *hardDisk);
};

//构造函数
void HardDiskFactory(struct HardDiskFactory *this);
//析构函数
void _HardDiskFactory(struct HardDiskFactory *this);

#endif