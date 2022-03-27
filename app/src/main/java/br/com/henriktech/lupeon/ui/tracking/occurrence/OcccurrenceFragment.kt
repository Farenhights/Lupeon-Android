package br.com.henriktech.lupeon.ui.tracking.occurrence

import android.content.Context
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        val binding = FragmentTrackingOccurrenceBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel(binding)
    }

    private fun startView(binding: FragmentTrackingOccurrenceBinding) {
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

    private fun startViewModel(binding: FragmentTrackingOccurrenceBinding) {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val invoice = sharedPref.getInt("INVOICE", 0)
        val cnpj = sharedPref.getString("CNPJ", "").toString()

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.titleOccurenceName.text = "${getString(R.string.hello)}, ${user!!.name}"
        }

        viewModel.getInvoice(invoice, cnpj)
    }
}