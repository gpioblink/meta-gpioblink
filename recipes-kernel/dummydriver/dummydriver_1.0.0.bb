# ref: https://developer.toradex.com/linux-bsp/os-development/build-yocto/custom-meta-layers-recipes-and-images-in-yocto-project-hello-world-examples/

# Package summary
SUMMARY = "Dummy Driver (Echo your Read/Write)"
# License, for example MIT
LICENSE = "GPLv2"
# License checksum file is always required
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"

inherit module

# hello-world.c from local file
SRC_URI = " \
    file://dummy.c \
    file://Makefile \
"

# Set LDFLAGS options provided by the build system
TARGET_CC_ARCH += "${LDFLAGS}"

# Change source directory to workdirectory where hello-world.cpp is
S = "${WORKDIR}"

RPROVIDES_${PN} += "kernel-module-dummydriver"
