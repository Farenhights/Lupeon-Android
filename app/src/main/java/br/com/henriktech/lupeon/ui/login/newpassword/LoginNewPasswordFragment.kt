package br.com.henriktech.lupeon.ui.login.newpassword

import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginNewPasswordFragment: Fragment(R.layout.fragment_newpassword) {

    private val analytics: LoginNewPasswordAnalytics by inject()
    private val viewModel: LoginNewPasswodViewModel by viewModel()

}