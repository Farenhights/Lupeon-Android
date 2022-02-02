package br.com.henriktech.lupeon.ui.login.losspassword

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentLosspasswordBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginLossPasswordFragment : Fragment(R.layout.fragment_losspassword) {

    private val analytics: LoginLossPasswordAnalytics by inject()
    private val viewModel: LoginLossPasswordViewModel by viewModel()

    private lateinit var binding: FragmentLosspasswordBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentLosspasswordBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        startView(binding)
        startViewModel()
    }

    private fun startView(binding: FragmentLosspasswordBinding) {
        binding.leftArrowView.setOnClickListener {
            analytics.clickBackButton()
            requireActivity().onBackPressed()
        }

        binding.sendEmailButton.setOnClickListener {
            analytics.clickSendEmailButton()
            viewModel.sendEmail(binding.textEmailView.text!!.toString())
        }

        binding.losspassCodeButton.setOnClickListener {
            analytics.clickSendEmailButton()
            findNavController().navigate(R.id.action_lossPasswordFragment_to_loginNewPasswordFragment)
        }
    }

    private fun startViewModel() {
        viewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }
}