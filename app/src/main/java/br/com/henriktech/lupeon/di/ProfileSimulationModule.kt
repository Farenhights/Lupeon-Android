package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.profile.simulation.ProfileSimulationAnalytics
import br.com.henriktech.lupeon.ui.profile.simulation.ProfileSimulationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ProfileSimulationModule {

    private val profileSimulationModule = module {
        single { ProfileSimulationAnalytics(get()) }
        viewModel { ProfileSimulationViewModel() }
    }

    fun get(): Module {
        return profileSimulationModule
    }
}