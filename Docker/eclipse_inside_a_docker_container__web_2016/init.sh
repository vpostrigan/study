#! /bin/bash

# Set d-bus machine-id
if [ ! -e /etc/machine-id ]; then
  dbus-uuidgen > /etc/machine-id
fi

# Properly start d-bus
mkdir -p /var/run/dbus
dbus-daemon --system

rm -f /tmp/.X*-lock
Xvnc -SecurityTypes=none :3 &
mutter -d :3 &
su me -c eclipse