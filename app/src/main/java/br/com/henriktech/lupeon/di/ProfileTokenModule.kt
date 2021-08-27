package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.profile.token.ProfileTokenAnalytics
import br.com.henriktech.lupeon.ui.profile.token.ProfileTokenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ProfileTokenModule {

    private val profileTokenModule = module {
        single { ProfileTokenAnalytics(get()) }
        viewModel { ProfileTokenViewModel() }
    }

    fun get(): Module {
        return profileTokenModule
    }
}