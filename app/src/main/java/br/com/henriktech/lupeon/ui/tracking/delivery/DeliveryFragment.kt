package br.com.henriktech.lupeon.ui.tracking.delivery

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentTrackingDeliveryBinding
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DeliveryFragment : Fragment(R.layout.fragment_tracking_delivery) {
    private val analytics: DeliveryAnalytics by inject()
    private val viewModel: DeliveryViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        val binding = FragmentTrackingDeliveryBinding.bind(view)
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel(binding)
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
        binding.buttonTrackingOccurrences.setOnClickListener {
            findNavController().navigate(R.id.action_deliveryFragment_to_ocorrencyFragment)
        }
    }

    private fun startViewModel(binding: FragmentTrackingDeliveryBinding) {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val invoice = sharedPref.getInt("INVOICE", 0)
        val cnpj = sharedPref.getString("CNPJ", "").toString()

        viewModel.user.observe(viewLifecycleOwner) { user ->
            binding.titleTrackingName.text = "${getString(R.string.hello)}, ${user!!.name}"
        }

        viewModel.invoice.observe(viewLifecycleOwner) {
            binding.trackingProgressBar.visibility = View.GONE
            binding.buttonTrackingOccurrences.visibility =
                if (it.Ocorrencias.isEmpty()) View.GONE else View.VISIBLE

            binding.textTrackingNumberInvoice.text = it.NumeroNFe
            binding.textTrackingRecipient.text = it.Transportadora
            binding.textTrackingOrigemCity.text = "${it.CidadeOrigem}/${it.UFOrigem}"
            binding.textTrackingDestinyCity.text = "${it.CidadeDestino}/${it.UFDestino}"
            val plural = if (it.PrazoEntrega > 1) "s" else ""
            binding.textTrackingDeadline.text = "${it.PrazoEntrega} dia" + plural
            binding.textTrackingDeliveryForecast.text = it.PrevisaoEntrega

            binding.imageProgressNF.isEnabled = it.PossuiNFe
            binding.imageProgressTrace1.isEnabled = it.PossuiCTe
            binding.imageProgressTransporter.isEnabled = it.PossuiCTe
            binding.imageProgressTrace2.isEnabled = it.Entregue
            binding.imageProgressDelivery.isEnabled = it.Entregue
            binding.imageProgressTrace3.isEnabled = it.Entregue
            binding.imageProgressDelivery.isEnabled = it.Entregue
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.trackingProgressBar.visibility = View.GONE
            binding.trackingNoFe.visibility = View.VISIBLE
            binding.textErrorMessage.text = it
        }

        viewModel.getInvoice(invoice, cnpj)
    }
}