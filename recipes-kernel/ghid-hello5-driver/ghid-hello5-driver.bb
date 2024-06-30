SUMMARY = "gHID Keyboard Driver for hello5"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://${COREBASE}/meta/files/common-licenses/GPL-2.0-or-later;md5=fed54355545ffd980b814dab4a3b312c"

inherit module

SRC_URI = " \
    file://driver.c \
    file://keyboard.h \
    file://Makefile \
"

TARGET_CC_ARCH += "${LDFLAGS}"

S = "${WORKDIR}"

RPROVIDES_${PN} += "kernel-module-ghid-hello5-driver"
