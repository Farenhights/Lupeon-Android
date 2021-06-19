package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.splash.SplashAnalytics
import org.koin.core.module.Module
import org.koin.dsl.module

object SplashModule {
    private val splashModule = module {
        single {
             SplashAnalytics(get())
        }
    }

    fun get(): Module {
        return splashModule
    }
}