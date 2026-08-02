package com.offsec.nhterm.component

import com.offsec.nhterm.component.session.SessionComponent
import com.offsec.nhterm.component.session.SessionComponentImpl

object ComponentManager {
    private val components = mutableMapOf<Class<*>, Any>()

    init {
        register(SessionComponent::class.java, SessionComponentImpl())
    }

    fun <T> register(clazz: Class<T>, instance: T) {
        components[clazz] = instance
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getComponent(clazz: Class<T>): T {
        return components[clazz] as? T
            ?: throw IllegalStateException("Component ${clazz.simpleName} not registered")
    }
}
