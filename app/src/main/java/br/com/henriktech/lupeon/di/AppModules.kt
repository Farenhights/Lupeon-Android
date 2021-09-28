package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.database.db.AppDataBase
import org.koin.core.context.loadKoinModules

object AppModules {
    fun loadModule(dataBase: AppDataBase) {
        loadKoinModules(
            listOf(
                SplashModule.get(),
                LoginPresentationModule.get(),
                LoginMainModule.get(dataBase),
                LoginLossPasswordModule.get(),
                LoginNewPasswordModule.get(),
                DriverModule.get(),
                ProfileAllowanceModule.get(),
                ProfileIndicatorsModule.get(),
                ProfileMenuModule.get(dataBase),
                ProfileSimulationModule.get(),
                ProfileTokenModule.get(),
                ProfileTrackingModule.get()
            )
        )
    }
}