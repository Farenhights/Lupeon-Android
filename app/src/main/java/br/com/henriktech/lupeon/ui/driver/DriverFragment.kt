package br.com.henriktech.lupeon.ui.driver

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.data.model.Menu
import br.com.henriktech.lupeon.databinding.FragmentDriverMenuBinding
import br.com.henriktech.lupeon.ui.base.IOnBackPressed
import br.com.henriktech.lupeon.ui.base.OnMenuClickListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DriverFragment : Fragment(R.layout.fragment_driver_menu), IOnBackPressed,
    OnMenuClickListener {
    private val analytics: DriverAnalytics by inject()
    private val viewModel: DriverViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        val binding = FragmentDriverMenuBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel(binding)
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    override fun onMenuClicked(menu: Menu) {
        // TODO("Not yet implemented")
    }

    private fun startView(binding: FragmentDriverMenuBinding) {
        binding.imageViewLogoutMenu.setOnClickListener {
            viewModel.logout()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startViewModel(binding: FragmentDriverMenuBinding) {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.textNameProfileMenu.text = "${getString(R.string.hello)}, ${user.name}"
            } else {
                findNavController().navigate(R.id.action_driverFragment_to_loginActivity)
            }

        }
        viewModel.getUser()
    }

}