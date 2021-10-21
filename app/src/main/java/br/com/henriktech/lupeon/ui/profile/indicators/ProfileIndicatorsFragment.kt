package br.com.henriktech.lupeon.ui.profile.indicators

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentIndicatorsBinding
import br.com.henriktech.lupeon.databinding.FragmentProfileBinding
import br.com.henriktech.lupeon.ui.base.BaseFragment
import br.com.henriktech.lupeon.ui.profile.IOnBackPressed
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileIndicatorsFragment: BaseFragment(R.layout.fragment_indicators), IOnBackPressed {

    private val analytics: ProfileIndicatorsAnalytics by inject()
    private val viewlModel: ProfileIndicatorsViewModel by viewModel()

    private lateinit var binding: FragmentIndicatorsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentIndicatorsBinding.bind(view)
        binding.viewModel = viewlModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView()
        startViewModel()

    }

    private fun startViewModel() {
        TODO("Not yet implemented")
    }

    private fun startView() {
        binding.imageViewLogoutProfileIndicators.apply {
            setOnClickListener {
                logoutApplication()
            }
        }
    }

    override fun onBackPressed(): Boolean {
       return true
    }

}