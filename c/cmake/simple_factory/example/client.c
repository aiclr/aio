#include "shared/hard_disk.h"
#include "shared/hard_disk_factory.h"
#include "stddef.h"

int main(void)
{
    struct HardDisk *hardDisk = NULL;
    struct HardDiskFactory hardDiskFactory;

    HardDiskFactory(&hardDiskFactory);
    // 创建 SeagateHardDisk
    hardDisk = hardDiskFactory.Create(&hardDiskFactory, HARD_DISK_SUPPLIER_SEAGATE);
    // 使用
    hardDisk->Operation(hardDisk);
    // 销毁 回收内存
    hardDiskFactory.Destory(&hardDiskFactory, hardDisk);

    hardDisk = hardDiskFactory.Create(&hardDiskFactory, HARD_DISK_SUPPLIER_TOSHIBA);
    hardDisk->Operation(hardDisk);
    hardDiskFactory.Destory(&hardDiskFactory, hardDisk);

    _HardDiskFactory(&hardDiskFactory);
}