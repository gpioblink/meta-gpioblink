# ref: https://developer.toradex.com/linux-bsp/os-development/build-yocto/custom-meta-layers-recipes-and-images-in-yocto-project-hello-world-examples/

# Copyright (C) 2017, Chris Simmonds (chris@2net.co.uk)
#
# LINUXDIR should point to the kerenl you are running on the target.
# If you are building with Buildroot, you would type something like:

# export ARCH=arm
# export CROSS_COMPILE=arm-buildroot-linux-gnueabi-
# export LINUXDIR=/home/chris/buildroot/output/build/linux-4.9.6
# make 

# If you are compiling with a Yocto Project SDK, everything should
# be set up when you source the environment-setup scipt so you can
# just type:

# make

# LINUXDIR ?= $(SDKTARGETSYSROOT)/usr/src/kernel

obj-m := dummydriver.o

SRC := $(shell pwd)

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC)

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	rm -f *.o *~ core .depend .*.cmd *.ko *.mod.c
	rm -f Module.markers Module.symvers modules.order
	rm -rf .tmp_versions Modules.symvers

