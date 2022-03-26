package br.com.henriktech.lupeon.ui.login.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentLoginBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginMainFragment : Fragment(R.layout.fragment_login) {

    private val analytics: LoginMainAnalytics by inject()
    private val viewModel: LoginMainViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        val binding = FragmentLoginBinding.bind(view)
        binding.viewModelLogin = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel(binding)
    }

    private fun startView(binding: FragmentLoginBinding) {
        binding.loginProgressBar.visibility = View.GONE
        binding.leftArrowView.setOnClickListener {
            analytics.clickBackButton()
            findNavController().navigate(R.id.action_mainFragment_to_loginActivity)
        }

        binding.buttonLossPasswordView.setOnClickListener {
            analytics.clickLossPasswordButton()
            findNavController().navigate(R.id.action_mainFragment_to_lossPasswordFragment)
        }

        binding.mainEnterButton.setOnClickListener {
            analytics.clickEnterButton()
            binding.loginProgressBar.visibility = View.VISIBLE
            viewModel.validateLogin(
                binding.textUserLoginView.text.toString(),
                binding.textPasswordLoginView.text.toString(),
                requireContext(),
            )
        }
    }

    private fun startViewModel(binding: FragmentLoginBinding) {
        viewModel.perfil.observe(viewLifecycleOwner) {
            analytics.typeLogin(it)
            if (it.equals("M")) {
                findNavController().navigate(R.id.action_mainFragment_to_driverFragment)
            } else {
                findNavController().navigate(R.id.action_mainFragment_to_profileMenuFragment)
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.loginProgressBar.visibility = View.GONE
            binding.loginMessageErroView.visibility = View.VISIBLE
        }
    }
}