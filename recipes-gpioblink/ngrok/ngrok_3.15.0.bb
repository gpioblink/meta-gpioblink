SUMMARY = "ngrok secure introspectable tunnels to localhost"
DESCRIPTION = "ngrok is a globally distributed reverse proxy that secures, protects and accelerates your applications and network services"
HOMEPAGE = "https://ngrok.com/"
LICENSE = "CLOSED"

SRC_URI = "https://bin.equinox.io/c/4VmDzA7iaHb/ngrok-stable-linux-arm.tgz"
SRC_URI[sha256sum] = "3516cb208051b41acf419e09a3ccf31e8d8fa5a76e9d175db11db9ad1ae22144"

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