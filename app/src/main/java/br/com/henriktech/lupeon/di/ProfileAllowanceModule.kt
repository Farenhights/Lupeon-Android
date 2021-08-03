package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.profile.allowance.ProfileAllowanceAnalytics
import br.com.henriktech.lupeon.ui.profile.allowance.ProfileAllowanceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ProfileAllowanceModule{
    private val profileAllowanceModule = module {
        single { ProfileAllowanceAnalytics(get()) }
        viewModel { ProfileAllowanceViewModel() }
    }

    fun get(): Module {
        return profileAllowanceModule
    }
}
