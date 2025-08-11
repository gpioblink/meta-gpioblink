#FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"
#SRC_URI += "file://defconfig"

# Prefer defconfig from SRC_URI; avoid duplicate with recipe's KBUILD_DEFCONFIG
#KBUILD_DEFCONFIG = ""
#KERNEL_DEFCONFIG = ""

# Ensure machine override also clears defconfig provided by meta-raspberrypi
#KBUILD_DEFCONFIG:raspberrypi0-wifi = ""

