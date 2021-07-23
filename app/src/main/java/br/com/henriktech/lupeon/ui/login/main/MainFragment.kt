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
import br.com.henriktech.lupeon.data.model.Profile
import br.com.henriktech.lupeon.ui.driver.DriverActivity
import br.com.henriktech.lupeon.ui.shipper.ShipperActivity
import br.com.henriktech.lupeon.ui.transporter.TransporterActivity
import org.koin.android.ext.android.inject

class MainFragment : Fragment(R.layout.fragment_login) {

    private val analytics: MainAnalytics by inject()
    private val viewModel: MainViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())

        view.findViewById<ImageView>(R.id.leftArrowView).apply {
            setOnClickListener {
                analytics.clickBackButton()
                requireActivity().onBackPressed()
            }
        }

        view.findViewById<TextView>(R.id.buttonLossPasswordView).apply {
            setOnClickListener {
                analytics.clickLossPasswordButton()
                it.findNavController().navigate(R.id.action_mainFragment_to_lossPasswordFragment)
            }
        }

        view.findViewById<Button>(R.id.mainEnterButton).apply {
            setOnClickListener {
                analytics.clickEnterButton()
                val user = view.findViewById<EditText>(R.id.textUserLoginView).text.toString()
                val password =
                    view.findViewById<EditText>(R.id.textPasswordLoginView).text.toString()
                viewModel.validateLogin(user, password)
                viewModel.setProfile(user)
            }
        }
        startViewModel()
    }

    private fun startViewModel() {
        viewModel.activityLiveData.observe(viewLifecycleOwner, Observer {
            startActivity(it)
        })
    }

    private fun startActivity(profile: Profile) {
        val intent =
            when(profile) {
                Profile.SHIPPER -> Intent(context, ShipperActivity::class.java)
                Profile.TRANSPORTER -> Intent(context, TransporterActivity::class.java)
                else -> Intent(context, DriverActivity::class.java)
            }
        startActivity(intent)
    }
}