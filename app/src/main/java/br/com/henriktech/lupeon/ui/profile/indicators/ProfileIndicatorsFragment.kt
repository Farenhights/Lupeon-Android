package br.com.henriktech.lupeon.ui.profile.indicators

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.base.BaseFragment
import br.com.henriktech.lupeon.ui.profile.IOnBackPressed
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileIndicatorsFragment: BaseFragment(R.layout.fragment_indicators), IOnBackPressed {

    private val analytics: ProfileIndicatorsAnalytics by inject()
    private val viewlModel: ProfileIndicatorsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<ImageView>(R.id.imageViewLogoutProfileIndicators).apply {
            setOnClickListener {
                logoutApplication()
            }
        }
    }

    override fun onBackPressed(): Boolean {
       return true
    }

}