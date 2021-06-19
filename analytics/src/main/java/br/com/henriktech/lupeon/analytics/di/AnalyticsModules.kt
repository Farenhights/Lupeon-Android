package br.com.henriktech.lupeon.analytics.di

import br.com.henriktech.lupeon.analytics.Analytics
import br.com.henriktech.lupeon.analytics.provider.AppCenterAnalyticsProvider
import br.com.henriktech.lupeon.analytics.provider.FirebaseAnalyticsProvider
import com.google.firebase.analytics.FirebaseAnalytics
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object AnalyticsModules {
    private val analyticsModule = module {
        single { Analytics(listOf(
            get<FirebaseAnalyticsProvider>(),
            get<AppCenterAnalyticsProvider>()
        )) }
        single { FirebaseAnalyticsProvider(get()) }
        single { AppCenterAnalyticsProvider(get()) }
        factory { FirebaseAnalytics.getInstance(get()) }
    }
    fun loadModule() {
        loadKoinModules(
            listOf(
                analyticsModule
            ))
    }
}