package br.com.henriktech.lupeon.ui.receipt

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentReceiptThirdBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiptThirstFragment : Fragment(R.layout.fragment_receipt_third) {

    private val analytics: ReceiptAnalytics by inject()
    private val viewModel: ReceiptViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        val binding = FragmentReceiptThirdBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel(binding)
    }

    private fun startView(binding: FragmentReceiptThirdBinding) {
        binding.buttonDialogIdentificationInvoiceClose.setOnClickListener {
            analytics.clickButton("buttonDialogIdentificationInvoiceClose")
            requireActivity().onBackPressed()
        }
        binding.buttonDelivered.setOnClickListener {
            analytics.clickButton("buttonDelivered")
        }
        binding.buttonOtherOccurrences.setOnClickListener {
            analytics.clickButton("buttonOtherOccurrences")
            //            viewModel.showOccurrences()
        }
    }

    private fun startViewModel(binding: FragmentReceiptThirdBinding) {

    }
}