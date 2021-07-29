package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.data.service.AppInfoService
import br.com.henriktech.lupeon.ui.login.presentention.LoginPresentationAnalytics
import br.com.henriktech.lupeon.ui.login.presentention.LoginPresentationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object PresentationModule {
    private val presentationModule = module {
        single { LoginPresentationAnalytics(get()) }
        single { AppInfoService() }
        viewModel { LoginPresentationViewModel(get()) }
    }

    fun get(): Module {
        return presentationModule
    }
}