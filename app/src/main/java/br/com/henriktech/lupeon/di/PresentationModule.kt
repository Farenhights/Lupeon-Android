package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.data.service.AppInfoService
import br.com.henriktech.lupeon.ui.login.presentention.PresentationAnalytics
import br.com.henriktech.lupeon.ui.login.presentention.PresentationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {
    private val presentationModule = module {
        single { PresentationAnalytics(get()) }
        single { AppInfoService() }
        viewModel { PresentationViewModel(get()) }
    }

    fun get(): Module {
        return presentationModule
    }
}