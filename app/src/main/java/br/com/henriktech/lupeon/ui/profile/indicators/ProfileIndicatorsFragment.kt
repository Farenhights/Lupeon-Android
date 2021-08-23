package br.com.henriktech.lupeon.ui.profile.indicators

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.base.BaseFragment
import br.com.henriktech.lupeon.ui.profile.IOnBackPressed

class ProfileIndicatorsFragment: BaseFragment(R.layout.fragment_indicators), IOnBackPressed {

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