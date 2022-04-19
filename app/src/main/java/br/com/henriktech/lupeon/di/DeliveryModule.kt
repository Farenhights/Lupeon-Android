package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.data.service.TrackingService
import br.com.henriktech.lupeon.ui.delivery.DeliveryAnalytics
import br.com.henriktech.lupeon.ui.delivery.DeliveryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object DeliveryModule {
    private val deliveryModule = module {
        single { TrackingService(get()) }
        single { DeliveryAnalytics(get()) }
        viewModel { DeliveryViewModel(get(), get()) }
    }

    fun get(): Module {
        return deliveryModule
    }
}