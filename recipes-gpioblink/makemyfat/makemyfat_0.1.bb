DESCRIPTION = "FAT tools for karaoke"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/gpioblink/mkmyfat.git;branch=main;protocol=https"
SRCREV = "859bffbf000489671ec9cf7832efc3abc4e83fc7"

UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "gpioblink.com/app/makemyfat"
GO_INSTALL = "${GO_IMPORT}"

export GO111MODULE="on"

inherit go go-mod

