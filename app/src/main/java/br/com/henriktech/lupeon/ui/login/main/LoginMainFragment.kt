package br.com.henriktech.lupeon.ui.login.main

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentLoginBinding
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginMainFragment : BaseFragment(R.layout.fragment_login) {

    private val analytics: LoginMainAnalytics by inject()
    private val viewModel: LoginMainViewModel by viewModel()

    private lateinit var binding: FragmentLoginBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentLoginBinding.bind(view)
        binding.viewModelLogin = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.leftArrowView.setOnClickListener {
            analytics.clickBackButton()
            requireActivity().onBackPressed()
        }

        binding.buttonLossPasswordView.setOnClickListener {
            analytics.clickLossPasswordButton()
            it.findNavController().navigate(R.id.action_mainFragment_to_lossPasswordFragment)
        }

        binding.mainEnterButton.setOnClickListener {
            analytics.clickEnterButton()
            binding.loginProgressBar.visibility = View.VISIBLE
            viewModel.validateLogin(
                binding.textUserLoginView.text.toString(),
                binding.textPasswordLoginView.text.toString()
            )
        }
        startViewModel()
    }

    override fun onStart() {
        super.onStart()
        binding.loginProgressBar.visibility = View.GONE
    }

    private fun startViewModel() {
        viewModel.user.observe(viewLifecycleOwner, { login ->
            analytics.typeLogin(login.tipoUsuario)
            loginApplication(login)
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, {
            binding.loginProgressBar.visibility = View.GONE
            binding.loginMessageErroView.visibility = View.VISIBLE
        })
    }
}