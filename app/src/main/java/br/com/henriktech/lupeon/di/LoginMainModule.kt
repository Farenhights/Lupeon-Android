package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.data.service.AuthenticationService
import br.com.henriktech.lupeon.database.repository.UserDbDataSource
import br.com.henriktech.lupeon.ui.login.main.LoginMainAnalytics
import br.com.henriktech.lupeon.ui.login.main.LoginMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginMainModule {
    private val loginMainModule = module {
        single { LoginMainAnalytics(get()) }
        single { AuthenticationService(get()) }
        single { UserDbDataSource(get()) }
        viewModel { LoginMainViewModel(get(), get()) }
    }

    fun get(): Module {
        return loginMainModule
    }
}