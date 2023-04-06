#include "shared/toshiba_hard_disk.h"
#include "stdio.h"

void ToshibaOperation(struct ToshibaHardDisk *this)
{
    printf("This is Toshiba Hard Disk");
}

void ToshibaHardDisk(struct ToshibaHardDisk *this)
{
    this->hardDisk.Operation = (void (*)(struct HardDisk *))ToshibaOperation;
}

void _ToshibaHardDisk(struct ToshibaHardDisk *this)
{
    this->hardDisk.Operation = NULL;
}