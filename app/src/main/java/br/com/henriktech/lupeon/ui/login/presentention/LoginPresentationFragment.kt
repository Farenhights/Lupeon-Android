package br.com.henriktech.lupeon.ui.login.presentention

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentPresentationBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginPresentationFragment : Fragment(R.layout.fragment_presentation) {

    private val analytics: LoginPresentationAnalytics by inject()
    private val viewModel: LoginPresentationViewModel by viewModel()

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
                findNavController().navigate(R.id.action_loginActivity_to_mainFragment)
            }
        }
    }
}