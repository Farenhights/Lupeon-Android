package br.com.henriktech.lupeon.ui.menu

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.data.model.Alert
import br.com.henriktech.lupeon.data.model.Menu
import br.com.henriktech.lupeon.databinding.FragmentMenuBinding
import br.com.henriktech.lupeon.ui.base.IOnBackPressed
import br.com.henriktech.lupeon.ui.base.OnMenuClickListener
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu), IOnBackPressed,
    AlertAdapter.OnAlertClickListener, OnMenuClickListener {
    private val analytics: MenuAnalytics by inject()
    private val viewModel: MenuViewModel by viewModel()
    private lateinit var webView: WebView
    private lateinit var binding: FragmentMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentMenuBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel()
    }

    override fun onMenuClicked(menu: Menu) {

        analytics.clickMenu(menu.option)

        when (menu.option) {
            "Indicadores" -> findNavController().navigate(R.id.action_menuFragment_to_indicatorsFragment)
            "Simulacao" -> findNavController().navigate(R.id.action_menuFragment_to_simulationFragment)
            "Tracking" -> findNavController().navigate(R.id.action_menuFragment_to_trackingFragment)
            else -> findNavController().navigate(R.id.action_menuFragment_to_loginActivity)
        }
    }

    override fun onAlertClicked(alert: Alert) {
        analytics.clickAlert(alert.title)
        showAlertDialog(alert)
    }

    override fun onBackPressed(): Boolean {
        return false
    }

    private fun startView(binding: FragmentMenuBinding) {
        binding.imageViewLogoutMenu.setOnClickListener {
            viewModel.logout()
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startViewModel() {
        viewModel.menus.observe(viewLifecycleOwner) { menus ->
            val recycleMenu: RecyclerView = binding.recycleMenuView
            val numberOfColumns = 2
            recycleMenu.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = MenuAdapter(menus, this)
            recycleMenu.adapter = adapter
        }

        viewModel.alerts.observe(viewLifecycleOwner) { alerts ->
            val recycleAlert: RecyclerView = binding.recycleAlertView
            val numberOfColumns = 1
            recycleAlert.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = AlertAdapter(alerts as ArrayList<Alert>, this)
            recycleAlert.adapter = adapter
        }

        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.textNameProfileMenu.text = "${getString(R.string.hello)}, ${user.name}"
                binding.textServiceSponsor.text = viewModel.getInfoService()
                binding.textContactSponsor.text = Html.fromHtml(
                    "GESTOR DA CONTA<br>${user.nameManager}" +
                            "<br>${user.contactsManager}", 0
                ).toString()
            } else {
                findNavController().navigate(R.id.action_menuFragment_to_loginActivity)
            }

        }
        viewModel.getUser()
    }

    private fun showAlertDialog(alert: Alert) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_alert)

        val title = dialog.findViewById(R.id.textDialogTitle) as TextView
        title.text = alert.title
        title.setTextColor(requireContext().getColor(R.color.gray_600))

        val close = dialog.findViewById(R.id.imageCloseDialog) as ImageView
        close.setOnClickListener { dialog.dismiss() }

        val body = dialog.findViewById(R.id.textDialogContent) as TextView
        body.text = alert.text
        body.setTextColor(requireContext().getColor(R.color.black))

        val link = dialog.findViewById(R.id.textDialogLink) as TextView
        link.text = alert.link
        link.setTextColor(requireContext().getColor(R.color.gray_600))
        link.setOnClickListener {
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(alert.link)
                    return true
                }
            }
            webView.loadUrl(alert.link)
        }
        dialog.show()
    }
}
