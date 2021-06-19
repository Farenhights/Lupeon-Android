package br.com.henriktech.lupeon.ui.login.presentention

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import br.com.henriktech.lupeon.BuildConfig
import br.com.henriktech.lupeon.R
import org.koin.android.ext.android.inject

class PresentationFragment : Fragment(R.layout.fragment_presentation) {

    private val analytics: PresentationAnalytics by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())

        view.findViewById<TextView>(R.id.contactsText).apply {
            text = Html.fromHtml(getString(R.string.contacts), 0)
        }

        view.findViewById<TextView>(R.id.versionText).apply {
            "${BuildConfig.VERSION_NAME}.${BuildConfig.VERSION_CODE}".also { text = it }
        }

       view.findViewById<Button>(R.id.presentationEnterButton).apply {
            setOnClickListener {
                analytics.clickEnterButton()
                it.findNavController().navigate(R.id.action_loginActivity_to_mainFragment)
            }
        }
    }
}