package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.login.losspassword.LoginLossPasswordAnalytics
import br.com.henriktech.lupeon.ui.login.losspassword.LoginLossPasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginLossPasswordModule {
    private val lossPasswordModule = module {
        single { LoginLossPasswordAnalytics(get()) }
        viewModel { LoginLossPasswordViewModel(get(), get()) }
    }

    fun get(): Module {
        return lossPasswordModule
    }
}