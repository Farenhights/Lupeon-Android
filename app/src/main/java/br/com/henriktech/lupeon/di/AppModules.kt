package br.com.henriktech.lupeon.di

import org.koin.core.context.loadKoinModules

object AppModules {
    fun loadModule() {
        loadKoinModules(
            listOf(
                SplashModule.get(),
                LoginPresentationModule.get(),
                LoginMainModule.get(),
                LoginLossPasswordModule.get(),
                LoginNewPasswordModule.get(),
                DriverModule.get(),
                ProfileMenuModule.get(),
                ProfileIndicatorsModule.get()
            )
        )
    }
}