package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.tracking.delivery.DeliveryAnalytics
import br.com.henriktech.lupeon.ui.tracking.delivery.DeliveryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object DeliveryModule {
    private val deliveryModule = module {
        single { DeliveryAnalytics(get()) }
        viewModel { DeliveryViewModel(get()) }
    }

    fun get(): Module {
        return deliveryModule
    }
}