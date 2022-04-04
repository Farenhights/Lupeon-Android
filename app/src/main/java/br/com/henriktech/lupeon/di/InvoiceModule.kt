package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.data.service.InvoiceService
import br.com.henriktech.lupeon.ui.invoice.InvoiceAnalytics
import br.com.henriktech.lupeon.ui.invoice.InvoiceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object InvoiceModule {
    private val invoiceModule = module {
        single { InvoiceAnalytics(get()) }
        single { InvoiceService() }
        viewModel { InvoiceViewModel(get(), get()) }
    }

    fun get(): Module {
        return invoiceModule
    }
}