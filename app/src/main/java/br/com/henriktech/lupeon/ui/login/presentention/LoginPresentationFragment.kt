package br.com.henriktech.lupeon.ui.login.presentention

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject

class LoginPresentationFragment : BaseFragment(R.layout.fragment_presentation) {

    val analytics: LoginPresentationAnalytics by inject()
    private val viewModel: LoginPresentationViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        startViewModel(view)
        startView(view)
    }

    private fun startView(view: View) {
        view.findViewById<Button>(R.id.presentationEnterButton).apply {
            setOnClickListener {
                analytics.clickEnterButton()
                it.findNavController().navigate(R.id.action_loginActivity_to_mainFragment)
            }
        }
    }

    private fun startViewModel(view: View) {
        viewModel.contactsLiveData.observe(viewLifecycleOwner, Observer {
            view.findViewById<TextView>(R.id.contactsText).apply {
                text = Html.fromHtml(it, 0)
            }
        })
        viewModel.versionLiveData.observe(viewLifecycleOwner, {
            view.findViewById<TextView>(R.id.versionText).apply {
                text = it
            }
        })
        viewModel.getInformations()
    }
}