package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.driver.DriverAnalytics
import br.com.henriktech.lupeon.ui.driver.DriverViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object DriverModule {

    private val driverModule = module {
        single { DriverAnalytics(get()) }
        viewModel { DriverViewModel(get(), get(), get()) }
    }

    fun get(): Module {
        return driverModule
    }
}