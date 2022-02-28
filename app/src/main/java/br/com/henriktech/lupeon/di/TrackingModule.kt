package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.tracking.home.TrackingAnalytics
import br.com.henriktech.lupeon.ui.tracking.home.TrackingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object TrackingModule {
    private val trackingModule = module {
        single { TrackingAnalytics(get()) }
        viewModel { TrackingViewModel(get()) }
    }

    fun get(): Module {
        return trackingModule
    }
}