package com.offsec.nhterm.component.session

import android.content.Context
import com.offsec.nhterm.backend.XSession

data class XParameter(
    val display: String = ":0",
    val width: Int = 800,
    val height: Int = 600,
    val density: Int = 160,
    val command: String? = null
)

class XSessionFactory {
    fun create(context: Context, parameter: XParameter): XSession {
        return XSession(context, parameter)
    }
}
