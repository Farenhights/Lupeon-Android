package br.com.henriktech.lupeon.ui.simulation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentSimulationBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class SimulationFragment: Fragment(R.layout.fragment_simulation){
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
        binding.imageViewLogoutSimulation.setOnClickListener {
            viewModel.logout()
        }
    }

    private fun startViewModel() {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user == null)
                findNavController().navigate(R.id.action_simulationFragment_to_loginActivity)
        }
        viewModel.getUser()
    }
}