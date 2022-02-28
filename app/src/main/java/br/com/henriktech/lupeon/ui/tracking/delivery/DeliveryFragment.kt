package br.com.henriktech.lupeon.ui.tracking.delivery

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentTrackingDeliveryBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeliveryFragment : Fragment(R.layout.fragment_tracking_delivery) {
    private val analytics: DeliveryAnalytics by inject()
    private val viewModel: DeliveryViewModel by viewModel()

    private lateinit var binding: FragmentTrackingDeliveryBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentTrackingDeliveryBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel()
    }

    private fun startView(binding: FragmentTrackingDeliveryBinding) {
    }

    private fun startViewModel() {
    }
}