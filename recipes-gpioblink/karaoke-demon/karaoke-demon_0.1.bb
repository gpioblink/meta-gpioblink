DESCRIPTION = "Demon for karaoke"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/gpioblink/karaoke-tools.git;branch=book-20250817;subpath=karaoke-demon;protocol=https"
SRCREV = "2d47eb81d1ed623999f2480ac6e2f389cd910c34"
UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "gpioblink.com/x/karaoke-demon"
GO_INSTALL = "${GO_IMPORT}/karaoke-demon"
export GO111MODULE="on"
inherit go go-mod

