package br.com.henriktech.lupeon.di

import org.koin.core.module.Module
import org.koin.dsl.module

object DriverModule {

    private val driverModule = module { }

    fun get(): Module {
        return driverModule
    }
}