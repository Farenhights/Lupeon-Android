package br.com.henriktech.lupeon.ui.login.losspassword

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import org.koin.android.ext.android.inject

class LossPasswordFragment: Fragment(R.layout.fragment_losspassword) {

    private val analytics: LossPasswordAnalytics by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())

        view.findViewById<TextView>(R.id.textLosspassView).apply {
            text = Html.fromHtml(getString(R.string.loss_my_password), 0)
        }

        view.findViewById<ImageView>(R.id.leftArrowView).apply {
            setOnClickListener {
                analytics.clickBackButton()
                requireActivity().onBackPressed()
            }
        }
    }
}