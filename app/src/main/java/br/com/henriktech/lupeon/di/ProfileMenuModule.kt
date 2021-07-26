package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.profile.menu.ProfileMenuAnalytics
import br.com.henriktech.lupeon.ui.profile.menu.ProfileMenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ProfileMenuModule {

    private val profileMenuModule = module {
        single { ProfileMenuAnalytics(get()) }
        viewModel { ProfileMenuViewModel() }
    }

    fun get(): Module {
        return profileMenuModule
    }
}