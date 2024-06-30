SUMMARY = "A simple demo script for g_HID"
DESCRIPTION = "Write report for typing hello 5 seconds each. THIS IS TYPING LOOP ONLY. You need to use other kernel module or so to activate a gadget HID device."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://ghid-hello5.sh file://ghid-hello5-loop.sh"

S = "${WORKDIR}"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/ghid-hello5.sh ${D}${sysconfdir}/init.d/ghid-hello5.sh
    install -m 0755 ${WORKDIR}/ghid-hello5-loop.sh ${D}${sysconfdir}/init.d/ghid-hello5-loop.sh
}

INITSCRIPT_NAME = "ghid-hello5.sh"
INITSCRIPT_PARAMS = "defaults"

FILES_${PN} += "${sysconfdir}/init.d/ghid-hello5.sh"
FILES_${PN} += "${sysconfdir}/init.d/ghid-hello5-loop.sh"
