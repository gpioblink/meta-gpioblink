DESCRIPTION = "Get the current read address from USB MSG via eBPF"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"

SRC_URI = "file://src/gpioblink.com/app/usbmsg-read/usbmsg-read.go file://src/gpioblink.com/app/usbmsg-read/main.go file://src/gpioblink.com/app/usbmsg-read/go.mod"

GO_IMPORT = "gpioblink.com/app/usbmsg-read"
GO_INSTALL = "${GO_IMPORT}"
#GO_WORKDIR = "${GO_INSTALL}"
export GO111MODULE="on"
inherit go

