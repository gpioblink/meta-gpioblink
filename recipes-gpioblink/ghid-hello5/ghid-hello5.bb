SUMMARY = "A simple demo script for g_HID and configfs"
DESCRIPTION = "Emulate a USB keyboard and type hello 5 seconds each."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://ghid-hello5.sh"

S = "${WORKDIR}"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/ghid-hello5.sh ${D}${sysconfdir}/init.d/ghid-hello5.sh
}

INITSCRIPT_NAME = "ghid-hello5.sh"
INITSCRIPT_PARAMS = "defaults"

FILES_${PN} += "${sysconfdir}/init.d/ghid-hello5.sh"

