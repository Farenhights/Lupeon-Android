package br.com.henriktech.lupeon.ui.profile.invoices

import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileInvoicesFragment: BaseFragment(R.layout.fragment_invoices) {
    private val analytics: ProfileInvoicesAnalytics by inject()
    private val viewModel: ProfileInvoicesViewModel by viewModel()
}