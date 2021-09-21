package br.com.henriktech.lupeon.ui.profile.menu

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.data.model.Alerta
import br.com.henriktech.lupeon.api.data.model.Menu
import br.com.henriktech.lupeon.ui.base.BaseFragment
import br.com.henriktech.lupeon.ui.profile.IOnBackPressed
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileMenuFragment : BaseFragment(R.layout.fragment_profile), IOnBackPressed,
    ProfileAlertAdapter.OnAlertClickListener, ProfileMenuAdapter.OnMenuClickListener {

    private val analytics: ProfileMenuAnalytics by inject()
    private val viewModel: ProfileMenuViewModel by viewModel()

    private lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        startView(view)
        startViewModel(view)
    }

    override fun onMenuClicked(menu: Menu) {

        analytics.clickMenu(menu.option)

        when (menu.option) {
            "Indicadores" -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileIndicatorsFragment)
            "Simulacao" -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileSimulationFragment)
            "Tracking" -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileTrackingFragment)
            "Faturas" -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileInvoicesFragment)
            "Abono" -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileAllowanceFragment)
            else -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileTokenFragment)
        }
    }

    override fun onAlertClicked(alert: Alerta) {
        analytics.clickAlert(alert.titulo)
        showAlertDialog(alert)
    }

    private fun startView(view: View) {
        view.findViewById<ImageView>(R.id.imageViewLogoutProfileMenu).apply {
            setOnClickListener {
                logoutApplication()
            }
        }
    }

    private fun startViewModel(view: View) {
        viewModel.menus.observe(viewLifecycleOwner, { menus ->
            val recycleMenu: RecyclerView = view.findViewById(R.id.recycleMenuView)
            val numberOfColumns = 2
            recycleMenu.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = ProfileMenuAdapter(menus as ArrayList<Menu>, this)
            recycleMenu.adapter = adapter
        })

        viewModel.alerts.observe(viewLifecycleOwner, { alerts ->
            val recycleAlert: RecyclerView = view.findViewById(R.id.recycleAlertView)
            val numberOfColumns = 1
            recycleAlert.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = ProfileAlertAdapter(alerts as ArrayList<Alerta>, this)
            recycleAlert.adapter = adapter
        })

        viewModel.name.observe(viewLifecycleOwner, { name ->
            view.findViewById<TextView>(R.id.textNameProfileMenu).apply { text = "Olá, $name" }
        })

        viewModel.setLogin(getLoginActive())
    }

    private fun showAlertDialog(alerta: Alerta) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_alert)

        val title = dialog.findViewById(R.id.textDialogTitle) as TextView
        title.text = alerta.titulo

        val close = dialog.findViewById(R.id.imageCloseDialog) as ImageView
        close.setOnClickListener { dialog.dismiss() }

        val body = dialog.findViewById(R.id.textDialogContent) as TextView
        body.text = alerta.texto

        val link = dialog.findViewById(R.id.textDialogLink) as TextView
        link.text = alerta.link
        link.setOnClickListener {
            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    view?.loadUrl(alerta.link)
                    return true
                }
            }
            webView.loadUrl(alerta.link)
        }
        dialog.show()
    }

    override fun onBackPressed(): Boolean {
        loginApplication(getLoginActive())
        return false
    }
}
