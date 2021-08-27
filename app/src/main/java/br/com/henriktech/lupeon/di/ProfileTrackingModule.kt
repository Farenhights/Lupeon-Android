package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.profile.tracking.ProfileTrackingAnalytics
import br.com.henriktech.lupeon.ui.profile.tracking.ProfileTrackingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ProfileTrackingModule {
    private val profileTrackingModule = module {
        single { ProfileTrackingAnalytics(get()) }
        viewModel { ProfileTrackingViewModel() }
    }

    fun get(): Module {
        return profileTrackingModule
    }
}