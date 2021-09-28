package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.repository.AlertDbDataSource
import br.com.henriktech.lupeon.database.repository.MenuDbDataSource
import br.com.henriktech.lupeon.database.repository.UserDbDataSource
import br.com.henriktech.lupeon.ui.profile.menu.ProfileMenuAnalytics
import br.com.henriktech.lupeon.ui.profile.menu.ProfileMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ProfileMenuModule {

    private lateinit var database: AppDataBase

    private val profileMenuModule = module {
        single { ProfileMenuAnalytics(get()) }
        viewModel {
            ProfileMenuViewModel(
                UserDbDataSource(database),
                MenuDbDataSource(database),
                AlertDbDataSource(database)
            )
        }
    }

    fun get(dataBase: AppDataBase): Module {
        this.database = dataBase
        return profileMenuModule
    }
}