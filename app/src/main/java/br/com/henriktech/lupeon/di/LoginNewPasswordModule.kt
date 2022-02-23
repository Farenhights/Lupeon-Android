package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.login.newpassword.LoginNewPasswodViewModel
import br.com.henriktech.lupeon.ui.login.newpassword.LoginNewPasswordAnalytics
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginNewPasswordModule {
    private val loginNewPasswordModule = module {
        single { LoginNewPasswordAnalytics(get()) }
        viewModel { LoginNewPasswodViewModel(get(), get()) }
    }

    fun get(): Module {
        return loginNewPasswordModule
    }
}