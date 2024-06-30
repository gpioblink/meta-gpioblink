#!/bin/sh

set -xeu

SERIALNUMBER="Hello"
MANUFACTURER="gpioblink-test"
PRODUCT_NAME="Keyboard Test"

modprobe libcomposite
modprobe dwc2
modprobe -r g_serial

# define directories
GADGET_DIR="/sys/kernel/config/usb_gadget/g1"
STRINGS_DIR="${GADGET_DIR}/strings/0x409"
CONFIG_DIR="${GADGET_DIR}/configs/c.1"
CONFIG_STRINGS_DIR="${CONFIG_DIR}/strings/0x409"
FUNCTIONS_DIR="${GADGET_DIR}/functions"
KEYBOARD_FUNCTION_DIR="${FUNCTIONS_DIR}/hid.usb0"
MOUSE_FUNCTION_DIR="${FUNCTIONS_DIR}/hid.usb1"

# setup the usb gadget

mkdir -p "${GADGET_DIR}"
echo 0x1d6b > "${GADGET_DIR}/idVendor"        # Linux Foundation
echo 0x0104 > "${GADGET_DIR}/idProduct"       # Multifunction Composite Gadget
echo 0x0010 > "${GADGET_DIR}/bcdDevice"       # v0.1.0
echo 0x0200 > "${GADGET_DIR}/bcdUSB"          # USB 2
echo 0x40   > "${GADGET_DIR}/bMaxPacketSize0" # 64

mkdir -p "${STRINGS_DIR}"
echo "${SERIALNUMBER}" > "${STRINGS_DIR}/serialnumber"
echo "${MANUFACTURER}" > "${STRINGS_DIR}/manufacturer"
echo "${PRODUCT_NAME}" > "${STRINGS_DIR}/product"

mkdir -p "${CONFIG_DIR}"
mkdir -p "${CONFIG_STRINGS_DIR}"
echo "Config 1: Keyboard Repeat Test" > "${CONFIG_STRINGS_DIR}/configuration"

# setup a keyboard
mkdir -p "${KEYBOARD_FUNCTION_DIR}" 
echo 1 > "${KEYBOARD_FUNCTION_DIR}/protocol"      # Keyboard
echo 0 > "${KEYBOARD_FUNCTION_DIR}/subclass"      # No Subclass
echo 8 > "${KEYBOARD_FUNCTION_DIR}/report_length" # 8 bytes

printf "\x05\x01\x09\x06\xA1\x01\x05\x07\x19\xE0\x29\xE7\x15\x00\x25\x01\x75\x01\x95\x08\x81\x02\x75\x08\x95\x01\x81\x01\x05\x08\x19\x01\x29\x05\x75\x01\x95\x05\x91\x02\x75\x03\x95\x01\x91\x01\x05\x07\x19\x00\x2A\xFF\x00\x15\x00\x26\xFF\x00\x75\x08\x95\x06\x81\x00\xC0" > /sys/kernel/config/usb_gadget/g1/functions/hid.usb0/report_desc

ln -s /sys/kernel/config/usb_gadget/g1/functions/hid.usb0 /sys/kernel/config/usb_gadget/g1/configs/c.1/

ls /dev
ls /sys/class/udc > /sys/kernel/config/usb_gadget/g1/UDC

/etc/init.d/ghid-hello5-loop.sh &
