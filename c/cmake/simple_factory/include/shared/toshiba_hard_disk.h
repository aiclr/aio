#ifndef TOSHIBA_HARD_DISK_H
#define TOSHIBA_HARD_DISK_H

#include "hard_disk.h"

struct ToshibaHardDisk
{
    struct HardDisk hardDisk;
};

// 构造函数
void ToshibaHardDisk(struct ToshibaHardDisk *this);

// 析构函数
void _ToshibaHardDisk(struct ToshibaHardDisk *this);

#endif