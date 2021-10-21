package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.profile.indicators.ProfileIndicatorsAnalytics
import br.com.henriktech.lupeon.ui.profile.indicators.ProfileIndicatorsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ProfileIndicatorsModule {
    private val profileIndicatorModule = module {
        single { ProfileIndicatorsAnalytics(get()) }
        viewModel { ProfileIndicatorsViewModel(get(), get()) }
    }

    fun get(): Module {
        return profileIndicatorModule
    }
}
