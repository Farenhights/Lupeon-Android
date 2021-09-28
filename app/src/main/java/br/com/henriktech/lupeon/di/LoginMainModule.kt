package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.data.service.AuthenticationService
import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.repository.AlertDbDataSource
import br.com.henriktech.lupeon.database.repository.MenuDbDataSource
import br.com.henriktech.lupeon.database.repository.UserDbDataSource
import br.com.henriktech.lupeon.ui.login.main.LoginMainAnalytics
import br.com.henriktech.lupeon.ui.login.main.LoginMainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object LoginMainModule {
    private lateinit var database: AppDataBase
    private val loginMainModule = module {
        single { LoginMainAnalytics(get()) }
        single { AuthenticationService(get()) }
        viewModel { LoginMainViewModel(
            get(),
            UserDbDataSource(database),
            MenuDbDataSource(database),
            AlertDbDataSource(database)) }
    }

    fun get(dataBase: AppDataBase): Module {
        this.database = dataBase
        return loginMainModule
    }
}