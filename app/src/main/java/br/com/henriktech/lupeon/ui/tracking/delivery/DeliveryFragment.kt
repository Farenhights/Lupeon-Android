package br.com.henriktech.lupeon.ui.tracking.delivery

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentTrackingDeliveryBinding
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY_PROPERTY_NAME
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
        binding.imageDeliveryArrowLetf.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.buttonMenuBottomShow.setOnClickListener {
            binding.menuBottom.visibility = View.VISIBLE
        }
        binding.buttonMenuBottomHide.setOnClickListener {
            binding.menuBottom.visibility = View.GONE
        }
    }

    private fun startViewModel() {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val invoice = sharedPref.getInt("INVOICE", 0)
        val cnpj = sharedPref.getString("CNPJ", "").toString()

        viewModel.getUser(invoice, cnpj)
    }
}