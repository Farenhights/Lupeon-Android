package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.data.service.AuthenticationService
import br.com.henriktech.lupeon.ui.login.main.MainAnalytics
import br.com.henriktech.lupeon.ui.login.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginMainModule {
    private val loginMainModule = module {
        single { MainAnalytics(get()) }
        single { AuthenticationService() }
        viewModel { MainViewModel(get()) }
    }

    fun get(): Module {
        return loginMainModule
    }
}