package br.com.henriktech.lupeon.ui.login.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.driver.DriverActivity
import br.com.henriktech.lupeon.ui.profile.ProfileActivity
import org.koin.android.ext.android.inject

class LoginMainFragment : Fragment(R.layout.fragment_login) {

    private val analyticsLogin: LoginMainAnalytics by inject()
    private val viewModelLogin: LoginMainViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analyticsLogin.trackScreen(requireActivity())

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
                val user = view.findViewById<EditText>(R.id.textUserLoginView).text.toString()
                val password =
                    view.findViewById<EditText>(R.id.textPasswordLoginView).text.toString()
                viewModelLogin.validateLogin(user, password)
            }
        }
        startViewModel()
    }

    private fun startViewModel() {
        viewModelLogin.login.observe(viewLifecycleOwner, Observer { login ->
            analyticsLogin.typeLogin(login.TipoUsuario)
            val intent =
                when(login.TipoUsuario){
                    "M" -> Intent(context, DriverActivity::class.java)
                    else -> Intent(context, ProfileActivity::class.java)
            }
            val bundle = Bundle()
            bundle.putParcelable("LOGIN", login)
            intent.putExtra("BUNDLE",bundle)
            startActivity(intent)
        })
    }
}