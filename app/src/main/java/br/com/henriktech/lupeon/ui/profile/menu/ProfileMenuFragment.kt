package br.com.henriktech.lupeon.ui.profile.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.Login
import org.koin.android.ext.android.inject

class ProfileMenuFragment: Fragment( R.layout.fragment_profile) {

    private val analytics: ProfileMenuAnalytics by inject()
    private val viewModel: ProfileMenuViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())

        val bundle = requireActivity().intent.getBundleExtra("BUNDLE")
        val login = bundle!!.getParcelable<Login>("LOGIN")!!
        viewModel.setLogin(login)

    }
}