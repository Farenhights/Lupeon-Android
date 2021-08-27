package br.com.henriktech.lupeon.ui.profile.allowance

import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileAllowanceFragment: BaseFragment(R.layout.fragment_allowance) {

    private val analytics: ProfileAllowanceAnalytics by inject()
    private val viewModel: ProfileAllowanceViewModel by viewModel()
}