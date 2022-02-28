package br.com.henriktech.lupeon.ui.tracking.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentTrackingBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackingFragment : Fragment(R.layout.fragment_tracking) {

    private val analytics: TrackingAnalytics by inject()
    private val viewModel: TrackingViewModel by viewModel()

    private lateinit var binding: FragmentTrackingBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentTrackingBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel()
    }

    private fun startView(binding: FragmentTrackingBinding) {
        binding.buttonTrackingLogout.setOnClickListener {
            viewModel.logout()
        }
        binding.imageTrackingArrowLetf.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.buttonMenuBottomShow.setOnClickListener {
            binding.menuBottom.visibility = View.VISIBLE
        }
        binding.buttonMenuBottomHide.setOnClickListener {
            binding.menuBottom.visibility = View.GONE
        }
        binding.trackingSearchButton.setOnClickListener {
            findNavController().navigate(R.id.action_trackingFragment_to_deliveryFragment)
        }
    }

    private fun startViewModel() {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user == null)
                findNavController().navigate(R.id.action_trackingFragment_to_loginActivity)
        }

        viewModel.getUser()
    }
}