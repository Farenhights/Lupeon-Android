package br.com.henriktech.lupeon

import android.app.Application
import br.com.henriktech.lupeon.analytics.di.AnalyticsModules
import br.com.henriktech.lupeon.api.ApiModules
import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.repository.AlertDbDataSource
import br.com.henriktech.lupeon.database.repository.MenuDbDataSource
import br.com.henriktech.lupeon.database.repository.UserDbDataSource
import br.com.henriktech.lupeon.di.AppModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class LupeonApp : Application() {

    private val database by lazy { AppDataBase.getDatabase(this@LupeonApp) }


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@LupeonApp)
            modules()
        }
        AnalyticsModules.loadModule()
        ApiModules.loadModule()
        AppModules.loadModule(database)
    }
}