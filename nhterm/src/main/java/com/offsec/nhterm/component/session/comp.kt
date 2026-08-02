package com.offsec.nhterm.component.session

interface SessionComponent {
    fun createSession(context: Context, parameter: ShellParameter): TerminalSession
    fun createSession(context: Context, parameter: XParameter): XSession
}

class SessionComponentImpl : SessionComponent {
    override fun createSession(context: Context, parameter: ShellParameter): TerminalSession {
        return TerminalSession(context, parameter)
    }

    override fun createSession(context: Context, parameter: XParameter): XSession {
        return XSession(context, parameter)
    }
}
