package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.login.main.MainAnalytics
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginMainModule {
    private val loginMainModule = module {
        single {
            MainAnalytics(get())
        }
    }

    fun get(): Module {
        return loginMainModule
    }
}