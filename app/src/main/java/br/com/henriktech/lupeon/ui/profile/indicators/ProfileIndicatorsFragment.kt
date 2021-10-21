package br.com.henriktech.lupeon.ui.profile.indicators

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.data.model.Indicator
import br.com.henriktech.lupeon.databinding.FragmentIndicatorsBinding
import br.com.henriktech.lupeon.ui.base.BaseFragment
import br.com.henriktech.lupeon.ui.profile.IOnBackPressed
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileIndicatorsFragment : BaseFragment(R.layout.fragment_indicators),
    IndicatorAdapter.OnIndicatorClickListener,
    IOnBackPressed {

    private val analytics: ProfileIndicatorsAnalytics by inject()
    private val viewModel: ProfileIndicatorsViewModel by viewModel()

    private lateinit var binding: FragmentIndicatorsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentIndicatorsBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView()
        startViewModel()
    }

    private fun startViewModel() {
        viewModel.indicators.observe(viewLifecycleOwner, { indicators ->
            val recycleIndicator: RecyclerView = binding.recycleIndicatorView
            val numberOfColumns = 2
            recycleIndicator.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = IndicatorAdapter(indicators as ArrayList<Indicator>, this)
            recycleIndicator.adapter = adapter
        })
        viewModel.getUser()
    }

    private fun startView() {
        binding.imageViewLogoutProfileIndicators.apply {
            setOnClickListener {
                logoutApplication()
            }
        }
    }

    override fun onBackPressed(): Boolean {
        return true
    }

    override fun onIndicatorClicked(indicator: Indicator) {
        TODO("Not yet implemented")
    }
}