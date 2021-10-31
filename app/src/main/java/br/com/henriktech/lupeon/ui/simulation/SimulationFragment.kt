package br.com.henriktech.lupeon.ui.simulation

import android.os.Bundle
import android.view.View
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentSimulationBinding
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimulationFragment: BaseFragment(R.layout.fragment_simulation) {
    private val analytics: SimulationAnalytics by inject()
    private val viewModel: SimulationViewModel by viewModel()

    private lateinit var binding: FragmentSimulationBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentSimulationBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel()
    }

    private fun startView(binding: FragmentSimulationBinding) {

    }

    private fun startViewModel() {
       //  TODO("Not yet implemented")
    }

}