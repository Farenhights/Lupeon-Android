package br.com.henriktech.lupeon.ui.invoice

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentInvoiceBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoiceFragment : Fragment(R.layout.fragment_invoice) {

    private val analytics: InvoiceAnalytics by inject()
    private val viewModel: InvoiceViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        val binding = FragmentInvoiceBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel(binding)
    }

    private fun startViewModel(binding: FragmentInvoiceBinding) {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.titleInvoiceName.text = "${getString(R.string.hello)}, ${user!!.name}"
        }
    }

    private fun startView(binding: FragmentInvoiceBinding) {
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
}