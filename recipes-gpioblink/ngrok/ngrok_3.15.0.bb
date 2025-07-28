SUMMARY = "ngrok secure introspectable tunnels to localhost"
DESCRIPTION = "ngrok is a globally distributed reverse proxy that secures, protects and accelerates your applications and network services"
HOMEPAGE = "https://ngrok.com/"
LICENSE = "CLOSED"

SRC_URI = "https://bin.equinox.io/c/bNyj1mQVY4c/ngrok-v3-stable-linux-arm.tgz"
SRC_URI[sha256sum] = "66c68eb5ee7da4fbc2bef3b2a98bc201edd29d8d1ce846c21c7888815183e766"

# For ARM architecture (Raspberry Pi Zero)
COMPATIBLE_MACHINE = "raspberrypi0.*"

S = "${WORKDIR}"

INSANE_SKIP:${PN} = "already-stripped"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

do_install() {
    install -d ${D}${bindir}
    install -m 0755 ${S}/ngrok ${D}${bindir}/ngrok
}

FILES:${PN} = "${bindir}/ngrok"