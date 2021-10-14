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
                DriverModule.get(),
                ProfileAllowanceModule.get(),
                ProfileIndicatorsModule.get(),
                ProfileMenuModule.get(),
                ProfileSimulationModule.get(),
                ProfileTokenModule.get(),
                ProfileTrackingModule.get()
            )
        )
    }
}