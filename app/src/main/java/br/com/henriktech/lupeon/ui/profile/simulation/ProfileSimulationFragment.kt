package br.com.henriktech.lupeon.ui.profile.simulation

import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileSimulationFragment: BaseFragment(R.layout.fragment_simulation) {
    private val analytics: ProfileSimulationAnalytics by inject()
    private val viewModel: ProfileSimulationViewModel by viewModel()
}