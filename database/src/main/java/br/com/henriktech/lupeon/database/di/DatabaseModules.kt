package br.com.henriktech.lupeon.database.di

import android.app.Application
import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.repository.*
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DatabaseModules {

    private val databaseModule = module {

        fun provideDatabase(application: Application): AppDataBase {
            return AppDataBase.getDatabase(application.applicationContext)
        }

        fun provideUserDao(database: AppDataBase): UserDbDataSource {
            return UserDbDataSource(database)
        }

        fun provideMenuDao(database: AppDataBase): MenuDbDataSource {
            return MenuDbDataSource(database)
        }

        fun provideAlertDao(database: AppDataBase): AlertDbDataSource {
            return AlertDbDataSource(database)
        }

        single { provideDatabase(androidApplication()) }
        factory<UserRepository> { provideUserDao(get()) }
        factory<MenuRepository> { provideMenuDao(get()) }
        factory<AlertRepository> { provideAlertDao(get()) }
    }

    fun loadModule() {
        loadKoinModules(
            listOf(
                databaseModule
            )
        )
    }
}
