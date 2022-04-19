package br.com.henriktech.lupeon.ui.tracking

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.data.model.Indicator
import br.com.henriktech.lupeon.databinding.FragmentTrackingBinding
import br.com.henriktech.lupeon.ui.base.IndicatorAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackingFragment : Fragment(R.layout.fragment_tracking) {

    private val analytics: TrackingAnalytics by inject()
    private val viewModel: TrackingViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        val binding = FragmentTrackingBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel(binding)
    }

    private fun startView(binding: FragmentTrackingBinding) {
        binding.buttonTrackingLogout.setOnClickListener {
            viewModel.logout()
        }
        binding.imageTrackingArrowLetf.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.buttonMenuBottomShow.setOnClickListener {
            binding.menuBottom.visibility = View.VISIBLE
        }
        binding.buttonMenuBottomHide.setOnClickListener {
            binding.menuBottom.visibility = View.GONE
        }
        binding.trackingSearchButton.setOnClickListener {
            val invoice = Integer.parseInt(binding.textNumberInvoice.text.toString())
            saveInvoice(invoice, binding.spinnerFilial.selectedItemPosition)
        }

    }

    private fun startViewModel(binding: FragmentTrackingBinding) {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user == null)
                findNavController().navigate(R.id.action_trackingFragment_to_loginActivity)
        }
        viewModel.indicators.observe(viewLifecycleOwner) { indicators ->
            val recycleIndicator: RecyclerView = binding.recycleTrackingView
            val numberOfColumns = 2
            recycleIndicator.layoutManager = GridLayoutManager(context, numberOfColumns)
            recycleIndicator.setHasFixedSize(true)
            val adapter = IndicatorAdapter(indicators as ArrayList<Indicator>)
            recycleIndicator.adapter = adapter
        }
        viewModel.filials.observe(viewLifecycleOwner) {
            val filtersAdapter = ArrayAdapter(
                requireContext(),
                R.layout.spinner_text_item,
                it,
            )
            binding.spinnerFilial.adapter = filtersAdapter
        }
        viewModel.getUser()
    }

    private fun saveInvoice(invoice: Int, selectedItemPosition: Int) {
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("INVOICE", invoice)
            putString("CNPJ", viewModel.getCNPJ(selectedItemPosition))
            apply()
        }
    }
}