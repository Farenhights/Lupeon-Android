package br.com.henriktech.lupeon

import android.app.Application
import br.com.henriktech.lupeon.analytics.di.AnalyticsModules
import br.com.henriktech.lupeon.api.ApiModules
import br.com.henriktech.lupeon.database.di.DatabaseModules
import br.com.henriktech.lupeon.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class LupeonApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LupeonApp)
        }
        AnalyticsModules.loadModule()
        ApiModules.loadModule()
        DatabaseModules.loadModule()
        AppModules.loadModule()
    }
}