package com.offsec.nhterm.bridge;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import java.lang.ref.WeakReference;
import java.io.File;

public class Runner extends AppCompatActivity {
    public static AppCompatActivity activity;
    public static WeakReference<Context> context = null;

    // التحقق من وجود صلاحيات الروت
    private static boolean hasRootAccess() {
        try {
            Process process = Runtime.getRuntime().exec("su -c exit");
            int exitCode = process.waitFor();
            return exitCode == 0;
        } catch (Exception e) {
            return false;
        }
    }

    // التحقق من وجود بيئة Kali
    private static boolean hasKaliRootfs() {
        File rootfs = new File("/data/data/com.termux/files/home/kali-arm64");
        return rootfs.exists() && rootfs.isDirectory();
    }

    // اختيار السكربت المناسب
    private static String getKaliShell() {
        if (hasRootAccess()) {
            // مع صلاحيات الروت → استخدام chroot
            return "/data/data/com.termux/files/usr/bin/kali";
        } else if (hasKaliRootfs()) {
            // بدون روت ولكن مع وجود بيئة → استخدام proot
            return "/data/data/com.termux/files/usr/bin/kali-proot";
        } else {
            // في حالة عدم وجود البيئة → استخدام السكربت الأصلي
            return "/data/data/com.termux/files/usr/bin/kali";
        }
    }

    public static void run_cmd(String cmd) {
        String shellPath = getKaliShell();
        Intent intent = Bridge.createExecuteIntent(shellPath, cmd);
        if (context != null && context.get() != null) {
            context.get().startActivity(intent);
        }
    }

    public static void run_cmd_android(String cmd) {
        Intent intent = Bridge.createExecuteIntent("/data/data/com.termux/files/usr/bin/android-su", cmd);
        if (context != null && context.get() != null) {
            context.get().startActivity(intent);
        }
    }

    public static void run_cmd_activity(String cmd) {
        String shellPath = getKaliShell();
        Intent intent = Bridge.createExecuteIntent(shellPath, cmd);
        if (activity != null) {
            activity.startActivity(intent);
        }
    }

    public static void run_cmd_android_aactivity(String cmd) {
        Intent intent = Bridge.createExecuteIntent("/data/data/com.termux/files/usr/bin/android-su", cmd);
        if (activity != null) {
            activity.startActivity(intent);
        }
    }
}
