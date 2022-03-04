package br.com.henriktech.lupeon.ui.indicators

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.data.model.Indicator
import br.com.henriktech.lupeon.databinding.FragmentIndicatorsBinding
import br.com.henriktech.lupeon.ui.base.IndicatorAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class IndicatorsFragment : Fragment(R.layout.fragment_indicators) {
    private val analytics: IndicatorsAnalytics by inject()
    private val viewModel: IndicatorsViewModel by viewModel()

    private lateinit var binding: FragmentIndicatorsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentIndicatorsBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel()
    }

    private fun startViewModel() {
        viewModel.indicators.observe(viewLifecycleOwner) { indicators ->
            val recycleIndicator: RecyclerView = binding.recycleIndicatorView
            val numberOfColumns = 2
            recycleIndicator.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = IndicatorAdapter(indicators as ArrayList<Indicator>)
            recycleIndicator.adapter = adapter
        }


        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user == null)
                findNavController().navigate(R.id.action_indicatorsFragment_to_loginActivity)
        }

        viewModel.transporters.observe(viewLifecycleOwner) {
            val filtersAdapter = ArrayAdapter(
                requireContext(),
                R.layout.spinner_text_item,
                it,
            )
            binding.spinnerTransporterFilter.adapter = filtersAdapter
        }

        viewModel.periods.observe(viewLifecycleOwner) {
            val filtersAdapter = ArrayAdapter(
                requireContext(),
                R.layout.spinner_text_item,
                it,
            )
            binding.spinnerPeriodFilter.adapter = filtersAdapter
        }

        viewModel.getUser()
    }

    private fun startView(binding: FragmentIndicatorsBinding) {
        binding.buttonLogoutIndicator.setOnClickListener {
            viewModel.logout()
        }
        binding.buttonMenuIndicator.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.imageIndicatorArrowLetf.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.buttonFilterIndicator.setOnClickListener {
            val token = viewModel.user.value!!.accessToken
            val companyId = viewModel.user.value!!.companyId.toInt()
            viewModel.getTransportersFilters(token, companyId)
            viewModel.getPeriodsFilters(token, companyId)
            binding.menuFilterIndicator.visibility = View.VISIBLE
        }
        binding.buttonCloseFilter.setOnClickListener {
            binding.menuFilterIndicator.visibility = View.GONE
        }
        binding.buttonCleanFilter.setOnClickListener {
            binding.menuFilterIndicator.visibility = View.GONE
        }
        binding.buttonApplyFilter.setOnClickListener {
            binding.menuFilterIndicator.visibility = View.GONE
        }
        binding.buttonMenuBottomShow.setOnClickListener {
            binding.menuBottom.visibility = View.VISIBLE
        }
        binding.buttonMenuBottomHide.setOnClickListener {
            binding.menuBottom.visibility = View.GONE
        }
    }

}