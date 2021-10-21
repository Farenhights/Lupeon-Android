package br.com.henriktech.lupeon.ui.profile.menu

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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.data.model.Alert
import br.com.henriktech.lupeon.data.model.Menu
import br.com.henriktech.lupeon.databinding.FragmentProfileBinding
import br.com.henriktech.lupeon.ui.base.BaseFragment
import br.com.henriktech.lupeon.ui.profile.IOnBackPressed
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProfileMenuFragment : BaseFragment(R.layout.fragment_profile), IOnBackPressed,
    ProfileAlertAdapter.OnAlertClickListener, ProfileMenuAdapter.OnMenuClickListener {

    private val analytics: ProfileMenuAnalytics by inject()
    private val viewModel: ProfileMenuViewModel by viewModel()

    private lateinit var webView: WebView

    private lateinit var binding: FragmentProfileBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        binding = FragmentProfileBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView()
        startViewModel()
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

    override fun onAlertClicked(alert: Alert) {
        analytics.clickAlert(alert.title)
        showAlertDialog(alert)
    }

    private fun startView() {
        binding.imageViewLogoutProfileMenu.apply {
            setOnClickListener {
                viewModel.logout()
                logoutApplication()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startViewModel() {
        viewModel.menus.observe(viewLifecycleOwner, { menus ->
            val recycleMenu: RecyclerView = binding.recycleMenuView
            val numberOfColumns = 2
            recycleMenu.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = ProfileMenuAdapter(menus, this)
            recycleMenu.adapter = adapter
        })

        viewModel.alerts.observe(viewLifecycleOwner, { alerts ->
            val recycleAlert: RecyclerView = binding.recycleAlertView
            val numberOfColumns = 1
            recycleAlert.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = ProfileAlertAdapter(alerts as ArrayList<Alert>, this)
            recycleAlert.adapter = adapter
        })

        viewModel.user.observe(viewLifecycleOwner, {
            val nome = it.name.split(" ")
            binding.textNameProfileMenu.text = "${getString(R.string.hello)}, ${nome[0]}"
            binding.textServiceSponsor.text = viewModel.getInfoService()
            binding.textContactSponsor.text = Html.fromHtml(
                "GESTOR DA CONTA<br>${it.nameManager}" +
                        "<br>${it.contactsManager}", 0
            ).toString()

        })

        viewModel.getUser()
    }

    private fun showAlertDialog(alerta: Alert) {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.dialog_alert)

        val title = dialog.findViewById(R.id.textDialogTitle) as TextView
        title.text = alerta.title

        val close = dialog.findViewById(R.id.imageCloseDialog) as ImageView
        close.setOnClickListener { dialog.dismiss() }

        val body = dialog.findViewById(R.id.textDialogContent) as TextView
        body.text = alerta.text

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
        return false
    }
}
