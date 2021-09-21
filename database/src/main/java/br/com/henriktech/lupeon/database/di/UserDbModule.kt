package br.com.henriktech.lupeon.database.di

import br.com.henriktech.lupeon.database.repository.UserDbDataSource
import org.koin.core.module.Module
import org.koin.dsl.module

object UserDbModule {
    private val userDbModule = module {
        single { UserDbDataSource(get()) }
    }

    fun get(): Module {
        return userDbModule
    }
}