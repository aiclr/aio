#include "shared/hard_disk_factory.h"
#include "shared/seagate_hard_disk.h"
#include "shared/toshiba_hard_disk.h"
#include "stdio.h"
#include "stdlib.h"

struct HardDisk *Create(struct HardDiskFactory *this, enum HARD_DISK_SUPPLIER_E supplier)
{
    switch (supplier)
    {
    case HARD_DISK_SUPPLIER_SEAGATE:
    {
        struct SeagateHardDisk *seagateHardDisk = NULL;
        if ((seagateHardDisk = malloc(sizeof(struct SeagateHardDisk))) == NULL)
        {
            printf("fail in malloc\n");
            return NULL;
        }
        SeagateHardDisk(seagateHardDisk);
        return (struct HardDisk *)seagateHardDisk;
    }
    case HARD_DISK_SUPPLIER_TOSHIBA:
    {
        struct ToshibaHardDisk *toshibaHardDisk = NULL;
        if ((toshibaHardDisk = malloc(sizeof(struct ToshibaHardDisk))) == NULL)
        {
            printf("fail in malloc\n");
            return NULL;
        }
        ToshibaHardDisk(toshibaHardDisk);
        return (struct HardDisk *)toshibaHardDisk;
    }
    default:
    {
        printf("Unknown Supplier\n");
        return NULL;
    }
    }
}

void Destory(struct HardDiskFactory *this, struct HardDisk *hardDisk)
{
    if (hardDisk != NULL)
    {
        free(hardDisk);
    }
}

void HardDiskFactory(struct HardDiskFactory *this)
{
    this->Create = Create;
    this->Destory = Destory;
}

void _HardDiskFactory(struct HardDiskFactory *this)
{
    this->Create = NULL;
    this->Destory = NULL;
}