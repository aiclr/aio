AR=ar
ARCH=x86_64
CC=gcc
DEBFLAGS=-O2
obj-m:=simple_chrdev.o
# KERNELDIR ?= /lib/modules/6.3.1-arch1-1/build/
KERNELDIR ?= /lib/modules/$(shell uname -r)/build/
PWD :=$(shell pwd)
modules:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) LDDINC=$(PWD)/../include modules
# .PHONY 一个虚假的目标并不是一个文件的真正名称；
# 相反，它只是当您发出明确请求时要执行的方法的名称。
# 使用虚假目标有两个原因：一是为了避免与同名文件发生冲突，二是为了提高性能。 
.PHONY: test-clean
clean: test-clean
	make -C $(KERNELDIR) M=$(PWD) clean
	# rm -rf *.o *.ko *.mod *.mod.c .*.cmd *.order *.symvers
test: test.o
	$(CC) -o test test.o
test-clean:
	rm -rf test