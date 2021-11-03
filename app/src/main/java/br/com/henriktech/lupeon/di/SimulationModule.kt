package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.simulation.SimulationAnalytics
import br.com.henriktech.lupeon.ui.simulation.SimulationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object SimulationModule {
    private val indicatorModule = module {
        single { SimulationAnalytics(get()) }
        viewModel { SimulationViewModel(get()) }
    }

    fun get(): Module {
        return indicatorModule
    }
}