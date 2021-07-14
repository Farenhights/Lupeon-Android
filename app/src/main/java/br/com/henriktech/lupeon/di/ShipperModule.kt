package br.com.henriktech.lupeon.di

import org.koin.core.module.Module
import org.koin.dsl.module

object ShipperModule {

    private val shipperModule = module {  }

    fun get(): Module {
        return shipperModule
    }
}