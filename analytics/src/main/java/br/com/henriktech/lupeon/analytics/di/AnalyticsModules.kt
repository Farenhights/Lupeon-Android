package br.com.henriktech.lupeon.analytics.di

import br.com.henriktech.lupeon.analytics.Analytics
import br.com.henriktech.lupeon.analytics.provider.AppCenterAnalyticsProvider
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AnalyticsModules {
    private val analyticsModule = module {
        single {
            Analytics(
                listOf(
                    get<AppCenterAnalyticsProvider>()
                )
            )
        }
        single { AppCenterAnalyticsProvider(get()) }
    }

    fun loadModule() {
        loadKoinModules(
            listOf(
                analyticsModule
            )
        )
    }
}