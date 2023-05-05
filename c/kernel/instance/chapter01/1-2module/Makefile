AR=ar
ARCH=x86_64
CC=gcc
DEBFLAGS=-O2
obj-m:=smodule.o
# KERNELDIR ?= /lib/modules/6.3.1-arch1-1/build/
KERNELDIR ?= /lib/modules/$(shell uname -r)/build/
PWD :=$(shell pwd)
modules:
	$(MAKE) -C $(KERNELDIR) M=$(PWD) LDDINC=$(PWD)/../include modules
clean:
	make -C $(KERNELDIR) M=$(PWD) clean
	# rm -rf *.o *.ko *.mod *.mod.c .*.cmd *.order *.symvers