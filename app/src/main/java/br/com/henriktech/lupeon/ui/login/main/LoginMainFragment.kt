package br.com.henriktech.lupeon.ui.login.main

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class LoginMainFragment : BaseFragment(R.layout.fragment_login) {

    private val analyticsLogin: LoginMainAnalytics by inject()
    private val viewModelLogin: LoginMainViewModel by inject()

    private lateinit var loginProgressBar: ConstraintLayout
    private lateinit var messageError: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analyticsLogin.trackScreen(requireActivity())
        loginProgressBar = view.findViewById<ConstraintLayout>(R.id.loginProgressBar)
        messageError = view.findViewById<TextView>(R.id.loginMessageErroView)
        view.findViewById<ImageView>(R.id.leftArrowView).apply {
            setOnClickListener {
                analyticsLogin.clickBackButton()
                requireActivity().onBackPressed()
            }
        }

        view.findViewById<TextView>(R.id.buttonLossPasswordView).apply {
            setOnClickListener {
                analyticsLogin.clickLossPasswordButton()
                it.findNavController().navigate(R.id.action_mainFragment_to_lossPasswordFragment)
            }
        }

        view.findViewById<Button>(R.id.mainEnterButton).apply {
            setOnClickListener {
                analyticsLogin.clickEnterButton()
                loginProgressBar.visibility = View.VISIBLE
                val user = view.findViewById<EditText>(R.id.textUserLoginView).text.toString()
                val password =
                    view.findViewById<EditText>(R.id.textPasswordLoginView).text.toString()
                viewModelLogin.validateLogin(user, password)
            }
        }
        startViewModel()
    }

    override fun onStart() {
        super.onStart()
        loginProgressBar.visibility = View.GONE
    }

    private fun startViewModel() {
        viewModelLogin.login.observe(viewLifecycleOwner, Observer { login ->
            if (login?.TipoUsuario != null) {
                analyticsLogin.typeLogin(login.TipoUsuario)
                loginApplication(login)
            }
        })

        viewModelLogin.errorMessage.observe(viewLifecycleOwner, Observer {
            loginProgressBar.visibility = View.GONE
            messageError.visibility = View.VISIBLE
            messageError.text = it
        })
    }
}