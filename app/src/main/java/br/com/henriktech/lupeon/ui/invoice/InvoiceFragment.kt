package br.com.henriktech.lupeon.ui.invoice

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.databinding.FragmentInvoiceBinding
import br.com.henriktech.lupeon.ui.base.InvoiceAdapter
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
        viewModel.invoiceList.observe(viewLifecycleOwner) { invoices ->
            val recycleInvoice: RecyclerView = binding.recycleInvoiceView
            val numberOfColumns = 1
            recycleInvoice.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = InvoiceAdapter(invoices)
            recycleInvoice.adapter = adapter
        }
        viewModel.getUser()
        startSearchInvoices()
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
}