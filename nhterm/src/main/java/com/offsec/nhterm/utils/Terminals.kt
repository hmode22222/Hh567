package com.offsec.nhterm.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.offsec.nhterm.backend.TerminalSession
import com.offsec.nhterm.backend.XSession
import com.offsec.nhterm.component.session.ShellParameter
import com.offsec.nhterm.component.session.XParameter

object Terminals {
    fun createSession(context: Context, parameter: ShellParameter): TerminalSession {
        return TerminalSession(context, parameter)
    }

    fun createSession(activity: AppCompatActivity, parameter: XParameter): XSession {
        return XSession(activity, parameter)
    }
}
