package br.com.henriktech.lupeon.ui.tracking

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackingFragment() : Fragment(R.layout.fragment_tracking) {

    private val analytics: TrackingAnalytics by inject()
    private val viewModel: TrackingViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        startViewModel()
    }

    private fun startViewModel() {
//        viewModel.user.observe(viewLifecycleOwner) { user ->
//            if (user == null)
//                logoutApplication()
//        }
    }
}