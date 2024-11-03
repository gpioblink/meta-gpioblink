DESCRIPTION = "LED fetcher for karaoke"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "git://github.com/gpioblink/karaoke-tools.git;branch=main;subpath=karaoke-remote;protocol=https"
SRCREV = "2a91c93436998aecf4193e0d15a5fb7c233029b7"
UPSTREAM_CHECK_COMMITS = "1"

GO_IMPORT = "gpioblink.com/app/karaoke-remote"
GO_INSTALL = "${GO_IMPORT}/karaoke-remote"
export GO111MODULE="on"
inherit go go-mod

