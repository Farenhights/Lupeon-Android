package br.com.henriktech.lupeon.ui.login.newpassword

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentNewpasswordBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginNewPasswordFragment : Fragment(R.layout.fragment_newpassword) {

    private val analytics: LoginNewPasswordAnalytics by inject()
    private val viewModel: LoginNewPasswodViewModel by viewModel()

    private lateinit var binding: FragmentNewpasswordBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentNewpasswordBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        startView(binding)
        startViewModel()
    }

    private fun startView(binding: FragmentNewpasswordBinding) {
        binding.leftArrowView.setOnClickListener {
            analytics.clickBackButton()
            requireActivity().onBackPressed()
        }

        binding.confirmNewPasswordButton.setOnClickListener {
            analytics.clickNewPasswordButton()
            viewModel.confirmNewPassword(
                binding.textTokenView.text.toString(),
                binding.textPasswordView.text.toString(),
                binding.textConfirmPasswordView.text.toString()
            )
        }
    }

    private fun startViewModel() {
        viewModel.message.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }
}