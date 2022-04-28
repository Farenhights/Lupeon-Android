package br.com.henriktech.lupeon.di

import br.com.henriktech.lupeon.ui.receipt.ReceiptAnalytics
import br.com.henriktech.lupeon.ui.receipt.ReceiptViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object ReceiptModule {
    private val receiptModule = module {
        single { ReceiptAnalytics(get()) }
        viewModel { ReceiptViewModel() }
    }

    fun get(): Module {
        return receiptModule
    }
}