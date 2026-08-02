package com.offsec.nhterm.component.session

import android.content.Context
import io.neolang.frontend.ConfigVisitor
import com.offsec.nhterm.App
import com.offsec.nhterm.R
import com.offsec.nhterm.backend.TerminalSession
import com.offsec.nhterm.bridge.SessionId
import com.offsec.nhterm.component.ComponentManager
import com.offsec.nhterm.component.colorscheme.ColorSchemeComponent
import com.offsec.nhterm.component.config.DefaultValues
import com.offsec.nhterm.component.config.NeoPreference
import com.offsec.nhterm.component.config.NeoTermPath
import com.offsec.nhterm.component.font.FontComponent
import com.offsec.nhterm.component.profile.NeoProfile
import com.offsec.nhterm.frontend.session.terminal.TermSessionCallback
import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService

// ... (الكود الأصلي يبقى كما هو حتى السطر 178)

class ShellTermSession private constructor(
    shellPath: String, cwd: String,
    args: Array<String>, env: Array<String>,
    changeCallback: SessionChangedCallback,
    private val initialCommand: String?,
    val shellProfile: ShellProfile
) : TerminalSession(shellPath, cwd, args, env, changeCallback) {

    var exitPrompt = App.get().getString(R.string.process_exit_prompt)

    override fun initializeEmulator(columns: Int, rows: Int) {
        super.initializeEmulator(columns, rows)

        // التحقق من استخدام proot أو chroot
        val isProot = shellPath.contains("proot") || !hasRootAccess()
        
        val backgroundExecutor: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
        backgroundExecutor.schedule(
            {
                if (!isProot) {
                    // System shell -> Kali chroot (الطريقة القديمة)
                    sendInitialCommand(shellProfile.initialCommand)
                    sendInitialCommand(initialCommand)
                } else {
                    // استخدام proot مباشرة (بدون تأخير)
                    sendInitialCommand(shellProfile.initialCommand)
                    sendInitialCommand(initialCommand)
                }
            }, if (isProot) 0 else 2, java.util.concurrent.TimeUnit.SECONDS)
    }

    private fun hasRootAccess(): Boolean {
        return try {
            val process = Runtime.getRuntime().exec("su -c exit")
            process.waitFor() == 0
        } catch (e: Exception) {
            false
        }
    }

    // باقي الكود يبقى كما هو
}
