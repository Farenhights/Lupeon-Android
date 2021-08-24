package br.com.henriktech.lupeon.ui.login.presentention

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentPresentationBinding
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class LoginPresentationFragment : BaseFragment(R.layout.fragment_presentation) {

    private val analytics: LoginPresentationAnalytics by inject()
    private val viewModel: LoginPresentationViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPresentationBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        analytics.trackScreen(requireActivity())
        viewModel.getInformations()

        binding.presentationEnterButton.apply {
            setOnClickListener {
                analytics.clickEnterButton()
                it.findNavController().navigate(R.id.action_loginActivity_to_mainFragment)
            }
        }
    }
}