package br.com.henriktech.lupeon.database.di

import org.koin.core.context.loadKoinModules

object DatabaseModules {
    fun loadModule() {
        loadKoinModules(
            listOf(
                UserDbModule.get(),
            )
        )
    }
}