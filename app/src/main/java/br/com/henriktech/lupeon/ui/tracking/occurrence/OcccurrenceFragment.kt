package br.com.henriktech.lupeon.ui.tracking.occurrence

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentTrackingOccurrenceBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class OcorrencyFragment : Fragment(R.layout.fragment_tracking_occurrence) {
    private val analytics: OccurrenceAnalytics by inject()
    private val viewModel: OccurrenceViewModel by viewModel()

    private lateinit var binding: FragmentTrackingOccurrenceBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentTrackingOccurrenceBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel()
    }

    private fun startView(binding: FragmentTrackingOccurrenceBinding) {
    }

    private fun startViewModel() {
    }
}