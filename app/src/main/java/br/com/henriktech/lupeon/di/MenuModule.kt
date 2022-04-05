package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.data.service.FilterService
import br.com.henriktech.lupeon.data.service.IndicatorsService
import br.com.henriktech.lupeon.ui.menu.MenuAnalytics
import br.com.henriktech.lupeon.ui.menu.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object MenuModule {

    private fun profileMenuModule() = module {
        single { MenuAnalytics(get()) }
        single { IndicatorsService(get()) }
        viewModel { MenuViewModel(get(), get(), get(), get(), get()) }
    }

    fun get(): Module {
        return profileMenuModule()
    }
}