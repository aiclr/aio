#include "shared/seagate_hard_disk.h"
#include "stdio.h"

void SeagateOperation(struct SeagateHardDisk *this)
{
    printf("This is Seagate Hard Disk\n");
}

void SeagateHardDisk(struct SeagateHardDisk *this)
{
    this->hardDisk.Operation = (void (*)(struct HardDisk *))SeagateOperation;
}

void _SeagateHardDisk(struct SeagateHardDisk *this)
{
    this->hardDisk.Operation = NULL;
}