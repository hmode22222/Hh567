package com.offsec.nhterm.ui.customize

import androidx.appcompat.app.AppCompatActivity

abstract class BaseCustomizeActivity : AppCompatActivity() {
    abstract fun saveSettings()
    abstract fun loadSettings()
}
