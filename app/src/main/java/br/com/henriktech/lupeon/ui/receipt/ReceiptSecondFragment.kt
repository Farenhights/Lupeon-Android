package br.com.henriktech.lupeon.ui.receipt

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentReceiptSecondBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiptSecondFragment : Fragment(R.layout.fragment_receipt_second) {

    private val analytics: ReceiptAnalytics by inject()
    private val viewModel: ReceiptViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        val binding = FragmentReceiptSecondBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel(binding)
    }

    private fun startView(binding: FragmentReceiptSecondBinding) {
        binding.imageSearchInvoiceArrowLetf.setOnClickListener {
            analytics.clickButton("imageSearchInvoiceArrowLetf")
            findNavController().navigate(R.id.action_receiptSecondFragment_to_driverFragment)
        }
        binding.buttonSearchInvoiceLogout.setOnClickListener {
            analytics.clickButton("buttonSearchInvoiceLogout")
            findNavController().navigate(R.id.action_receiptSecondFragment_to_receiptThirstFragment)
        }
    }

    private fun startViewModel(binding: FragmentReceiptSecondBinding) {

    }
}