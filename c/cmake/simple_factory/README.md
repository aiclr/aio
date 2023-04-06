# 简单工厂方法

## 简介

> 简单工厂方法定义一个用于创建对象的类，该类接受一个参数，通过参数决定创建不同的对象。 \
> GOF并没有把简单工厂方法定义为23种设计模式之一，可以认为简单工厂方法是工厂方法的简化形式。

## 实现思路

> Product: HardDisk 定义硬盘对象 \
> Concrete<sub>具体的</sub> Product: SeagateHardDisk、ToshibaHardDisk 实现不同供应商的硬盘 \
> SimpleFactory: HardDiskFactory 根据参数，创建不同供应商的硬盘对象