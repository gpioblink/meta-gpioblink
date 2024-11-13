DESCRIPTION = "Demon for karaoke"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/gpioblink/karaoke-tools.git;branch=main;subpath=karaoke-demon;protocol=https"
SRCREV = "1d0bb58528abf6501aee76b12a26735b6ac56245"
UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "gpioblink.com/x/karaoke-demon"
GO_INSTALL = "${GO_IMPORT}/karaoke-demon"
export GO111MODULE="on"
inherit go go-mod

