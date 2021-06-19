package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.login.presentention.PresentationAnalytics
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {
    private val presentationModule = module {
        single {
            PresentationAnalytics(get())
        }
    }

    fun get(): Module {
        return presentationModule
    }
}