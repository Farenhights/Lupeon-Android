package br.com.henriktech.lupeon.ui.driver.menu

import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuDriverFragment: BaseFragment(0) {

    private val analytics: MenuDriverAnalytics by inject()
    private val viewModel: MenuDriverViewModel by viewModel()
}