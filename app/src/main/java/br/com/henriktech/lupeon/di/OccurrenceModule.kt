package br.com.henriktech.lupeon.di


import br.com.henriktech.lupeon.ui.occurrence.OccurrenceAnalytics
import br.com.henriktech.lupeon.ui.occurrence.OccurrenceViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

object OccurrenceModule {
    private val occurrenceModule = module {
        single { OccurrenceAnalytics(get()) }
        viewModel { OccurrenceViewModel(get(), get()) }
    }

    fun get(): Module {
        return occurrenceModule
    }
}