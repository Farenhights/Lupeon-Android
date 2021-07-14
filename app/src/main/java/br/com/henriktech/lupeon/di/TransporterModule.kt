package br.com.henriktech.lupeon.di

import org.koin.core.module.Module
import org.koin.dsl.module

object TransporterModule {

    private val transporterModule = module {  }

    fun get(): Module {
        return transporterModule
    }
}