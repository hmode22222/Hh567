#!/system/bin/sh

# سكربت تشغيل Kali باستخدام proot (بدون روت)
KALI_ROOT="/data/data/com.termux/files/home/kali-arm64"
HOME_DIR="/root"

if [ ! -d "$KALI_ROOT" ]; then
    echo "[!] Kali rootfs غير موجود: $KALI_ROOT"
    echo "[*] قم بتثبيته باستخدام: proot-distro install kali"
    exit 1
fi

exec proot \
    --link2symlink \
    -0 \
    -r "$KALI_ROOT" \
    -b /dev \
    -b /proc \
    -b /sys \
    -b /data/data/com.termux/files/usr/tmp:/tmp \
    -b /sdcard:/sdcard \
    -b /storage:/storage \
    -b /mnt:/mnt \
    -w "$HOME_DIR" \
    /usr/bin/env -i \
    HOME="$HOME_DIR" \
    PATH="/usr/local/sbin:/usr/local/bin:/bin:/usr/bin:/sbin:/usr/sbin" \
    TERM="$TERM" \
    LANG="C.UTF-8" \
    LC_ALL="C.UTF-8" \
    /bin/bash --login "$@"
