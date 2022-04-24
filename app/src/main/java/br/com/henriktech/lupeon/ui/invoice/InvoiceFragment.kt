package br.com.henriktech.lupeon.ui.invoice

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.response.Invoice
import br.com.henriktech.lupeon.databinding.FragmentInvoiceBinding
import br.com.henriktech.lupeon.ui.base.InvoiceAdapter
import br.com.henriktech.lupeon.ui.base.OnInvoiceClickListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class InvoiceFragment : Fragment(R.layout.fragment_invoice), OnInvoiceClickListener {

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
        viewModel.invoiceList.observe(viewLifecycleOwner) { invoices ->
            val recycleInvoice: RecyclerView = binding.recycleInvoiceView
            val numberOfColumns = 1
            recycleInvoice.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = InvoiceAdapter(invoices, this)
            recycleInvoice.adapter = adapter
            binding.invoiceProgressBar.visibility = View.GONE
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) {
            binding.invoiceProgressBar.visibility = View.GONE
            binding.buttonReload.visibility = View.VISIBLE
        }
        startSearchInvoices()
    }

    private fun startView(binding: FragmentInvoiceBinding) {
        binding.imageDeliveryArrowLeft.setOnClickListener {
            analytics.clickBack()
            requireActivity().onBackPressed()
        }
        binding.buttonMenuBottomShow.setOnClickListener {
            analytics.clickBottomMenuShow()
            binding.menuBottom.visibility = View.VISIBLE
        }
        binding.buttonMenuBottomHide.setOnClickListener {
            analytics.clickBottomMenuHide()
            binding.menuBottom.visibility = View.GONE
        }
        binding.buttonReload.setOnClickListener {
            analytics.clickReload()
            binding.invoiceProgressBar.visibility = View.VISIBLE
            binding.buttonReload.visibility = View.GONE
            startSearchInvoices()
        }
    }

    private fun startSearchInvoices() {

        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val token = sharedPref.getString("Token", "")
        val companyId = sharedPref.getInt("CompanyId", 0)
        val dataInicio = sharedPref.getString("DataInicio", "2021-11-01 00:00")
        val dataFim = sharedPref.getString("DataFim", "2021-11-03 00:00")
        val destinatarioId = sharedPref.getInt("DestinatarioId", 0)
        val periodoId = sharedPref.getInt("PeriodoId", 0)
        val statusId = sharedPref.getInt("StatusId", 1)

        if (dataInicio != null && dataFim != null && token != null) {
            viewModel.getInvoices(
                token,
                companyId,
                dataInicio,
                dataFim,
                destinatarioId,
                periodoId,
                statusId
            )
        }
    }

    override fun onInvoiceClickListener(invoice: Invoice) {
        val numberInvoice = Integer.parseInt(invoice.NumeroNfe)
        val custonDestiny = invoice.Destinatario.splitToSequence(" - ")
        val cnpj = custonDestiny.firstOrNull()
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("INVOICE", numberInvoice)
            putString("CNPJ", cnpj)
            apply()
        }
        findNavController().navigate(R.id.action_invoiceFragment_to_deliveryFragment)
    }
}