package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.login.losspassword.LoginLossPasswordAnalytics
import org.koin.core.module.Module
import org.koin.dsl.module

object LossPasswordModule {
    private val lossPasswordModule = module {
        single {
            LoginLossPasswordAnalytics(get())
        }
    }

    fun get(): Module {
        return lossPasswordModule
    }
}