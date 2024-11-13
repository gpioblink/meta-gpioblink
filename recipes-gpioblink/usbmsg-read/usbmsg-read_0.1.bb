DESCRIPTION = "Get the current read address from USB MSG via eBPF"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/gpioblink/karaoke-tools.git;branch=main;subpath=usbmsg-read;protocol=https"
SRCREV = "02573f44d81e901dfc470a148ced6b4327b65600"
UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "github.com/app/karaoke-tools"
GO_INSTALL = "${GO_IMPORT}/usbmsg-read"
export GO111MODULE="on"
inherit go go-mod

