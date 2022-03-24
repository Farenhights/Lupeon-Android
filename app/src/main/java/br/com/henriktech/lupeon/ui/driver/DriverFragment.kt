package br.com.henriktech.lupeon.ui.driver

import android.webkit.WebView
import androidx.fragment.app.Fragment
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.data.model.Menu
import br.com.henriktech.lupeon.databinding.FragmentDriverMenuBinding
import br.com.henriktech.lupeon.ui.base.IOnBackPressed
import br.com.henriktech.lupeon.ui.base.OnMenuClickListener
import br.com.henriktech.lupeon.ui.menu.MenuAnalytics
import br.com.henriktech.lupeon.ui.menu.MenuViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class DriverFragment : Fragment(R.layout.fragment_driver_menu), IOnBackPressed,
    OnMenuClickListener {
    private val analytics: MenuAnalytics by inject()
    private val viewModel: MenuViewModel by viewModel()
    private lateinit var webView: WebView
    private lateinit var binding: FragmentDriverMenuBinding

    override fun onBackPressed(): Boolean {
        TODO("Not yet implemented")
    }

    override fun onMenuClicked(menu: Menu) {
        TODO("Not yet implemented")
    }


}