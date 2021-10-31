package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.indicators.IndicatorsAnalytics
import br.com.henriktech.lupeon.ui.indicators.IndicatorsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object IndicatorsModule {
    private val indicatorModule = module {
        single { IndicatorsAnalytics(get()) }
        viewModel { IndicatorsViewModel(get(), get()) }
    }

    fun get(): Module {
        return indicatorModule
    }
}
