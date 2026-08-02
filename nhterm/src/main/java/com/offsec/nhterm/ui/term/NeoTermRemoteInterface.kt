package com.offsec.nhterm.ui.term

interface NeoTermRemoteInterface {
    fun executeCommand(command: String): String
    fun getTerminalState(): String
}
