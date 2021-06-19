package br.com.henriktech.lupeon.di

import org.koin.core.context.loadKoinModules

object AppModules {
    fun loadModule() {
        loadKoinModules(listOf(
            SplashModule.get(),
            PresentationModule.get(),
            LoginMainModule.get(),
            LossPasswordModule.get()
        ))
    }
}