SUMMARY = "Init karaoke-demon and supporting tools"
DESCRIPTION = "Init karaoke-demon and supporting tools"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://karaoke-init.sh"

S = "${WORKDIR}"

inherit update-rc.d

do_install() {
    install -d ${D}${sysconfdir}/init.d
    install -m 0755 ${WORKDIR}/karaoke-init.sh ${D}${sysconfdir}/init.d/karaoke-init.sh
}

INITSCRIPT_NAME = "karaoke-init.sh"
INITSCRIPT_PARAMS = "defaults"

FILES_${PN} += "${sysconfdir}/init.d/karaoke-init.sh"
