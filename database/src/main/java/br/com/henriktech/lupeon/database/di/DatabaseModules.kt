package br.com.henriktech.lupeon.database.di

import android.app.Application
import androidx.room.Room
import br.com.henriktech.lupeon.database.db.AppDataBase
import br.com.henriktech.lupeon.database.db.dao.AlertDao
import br.com.henriktech.lupeon.database.db.dao.MenuDao
import br.com.henriktech.lupeon.database.db.dao.UserDao
import org.koin.android.ext.koin.androidApplication
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

object DatabaseModules {

    private val databaseModule = module {

        fun provideDatabase(application: Application): AppDataBase {
            return Room.databaseBuilder(application, AppDataBase::class.java, "lupeon")
                .fallbackToDestructiveMigration()
                .build()
        }

        fun provideUserDao(database: AppDataBase): UserDao {
            return database.userDao()
        }

        fun provideMenuDao(database: AppDataBase): MenuDao {
            return database.menuDao()
        }

        fun provideAlertDao(database: AppDataBase): AlertDao {
            return database.alertDao()
        }

        single { provideDatabase(androidApplication()) }
        single { provideUserDao(get()) }
        single { provideMenuDao(get()) }
        single { provideAlertDao(get()) }
    }

    fun loadModule() {
        loadKoinModules(
            listOf(
                databaseModule
            )
        )
    }
}
