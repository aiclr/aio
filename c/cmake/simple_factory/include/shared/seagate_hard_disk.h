#ifndef SEAGATE_HARD_DISK_H
#define SEAGATE_HARD_DISK_H

#include "hard_disk.h"

struct SeagateHardDisk
{
    struct HardDisk hardDisk;
};

// 构造函数
void SeagateHardDisk(struct SeagateHardDisk *this);

// 析构函数
void _SeagateHardDisk(struct SeagateHardDisk *this);

#endif