package br.com.henriktech.lupeon.ui.receipt

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentReceiptFirstBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiptFirstFragment : Fragment(R.layout.fragment_receipt_first) {

    private val analytics: ReceiptAnalytics by inject()
    private val viewModel: ReceiptViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        val binding = FragmentReceiptFirstBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel(binding)
    }

    private fun startView(binding: FragmentReceiptFirstBinding) {
        binding.buttonDialogClose.setOnClickListener {
            analytics.clickButton("buttonDialogClose")
            findNavController().navigate(R.id.action_receiptFirstFragment_to_driverFragment)
        }
        binding.buttonInvoiceRead.setOnClickListener {
            analytics.clickButton("buttonInvoiceRead")
            findNavController().navigate(R.id.action_receiptFirstFragment_to_receiptSecondFragment)
        }
        binding.buttonCTERead.setOnClickListener {
            analytics.clickButton("buttonCTERead")
        }
    }

    private fun startViewModel(binding: FragmentReceiptFirstBinding) {

    }


}