#!/bin/sh

set -xeu

mkfifo /tmp/karaoke-fifo

karaoke-demon &
karaoke-remote &
usbmsg-read &

sleep 60
modprobe g_mass_storage file=/home/root/karaoke.img removable=y readonly=y

