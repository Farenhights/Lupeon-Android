package br.com.henriktech.lupeon.ui.occurrence

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.response.Ocorrencia
import br.com.henriktech.lupeon.databinding.FragmentTrackingOccurrenceBinding
import br.com.henriktech.lupeon.ui.base.OccurrenceAdapter
import br.com.henriktech.lupeon.ui.base.OnOccurrenceClickListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class OccurrenceFragment : Fragment(R.layout.fragment_tracking_occurrence),
    OnOccurrenceClickListener {

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

    override fun onOccurrenceClicked(occurrence: Ocorrencia) {
        Toast.makeText(context, occurrence.OcorrenciaLupeon, Toast.LENGTH_LONG).show()
    }

    private fun startView(binding: FragmentTrackingOccurrenceBinding) {
        binding.imageDeliveryArrowLeft.setOnClickListener {
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

        viewModel.occurrenceList.observe(viewLifecycleOwner) { occurrences ->
            val recycleOccurrences: RecyclerView = binding.recycleOccurrenceView
            val numberOfColumns = 1
            recycleOccurrences.layoutManager = GridLayoutManager(context, numberOfColumns)
            recycleOccurrences.setHasFixedSize(true)
            val adapter = OccurrenceAdapter(occurrences, this)
            recycleOccurrences.adapter = adapter
        }

        viewModel.getInvoice(invoice, cnpj)
    }
}