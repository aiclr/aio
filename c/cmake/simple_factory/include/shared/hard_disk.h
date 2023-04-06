#ifndef HARD_DISK_H
#define HARD_DISK_H

struct HardDisk
{
    void (*Operation)(struct HardDisk *this);
};

#endif