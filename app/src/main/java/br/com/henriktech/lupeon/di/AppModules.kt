package br.com.henriktech.lupeon.di

import org.koin.core.context.loadKoinModules

object AppModules {
    fun loadModule() {
        loadKoinModules(
            listOf(
                LoginPresentationModule.get(),
                LoginMainModule.get(),
                LoginLossPasswordModule.get(),
                LoginNewPasswordModule.get(),
                IndicatorsModule.get(),
                SimulationModule.get(),
                TrackingModule.get(),
                ProfileMenuModule.get()
            )
        )
    }
}