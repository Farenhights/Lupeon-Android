package br.com.henriktech.lupeon.ui.login.losspassword

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginLossPasswordFragment : BaseFragment(R.layout.fragment_losspassword) {

    private val analytics: LoginLossPasswordAnalytics by inject()
    private val viewModel: LoginLossPasswordViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())

        view.findViewById<TextView>(R.id.textLosspassView).apply {
            text = Html.fromHtml(getString(R.string.loss_my_password), 0)
        }

        view.findViewById<TextView>(R.id.textLossPassword).apply {
            text = Html.fromHtml(getString(R.string.text_loss_password), 0)
        }


        view.findViewById<ImageView>(R.id.leftArrowView).apply {
            setOnClickListener {
                analytics.clickBackButton()
                requireActivity().onBackPressed()
            }
        }
    }
}