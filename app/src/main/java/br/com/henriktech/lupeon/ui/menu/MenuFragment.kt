package br.com.henriktech.lupeon.ui.menu

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
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
import br.com.henriktech.lupeon.ui.base.MenuAdapter
import br.com.henriktech.lupeon.ui.base.OnMenuClickListener
import br.com.henriktech.lupeon.ui.driver.DialogClick
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu), IOnBackPressed,
    AlertAdapter.OnAlertClickListener, OnMenuClickListener {
    private val analytics: MenuAnalytics by inject()
    private val viewModel: MenuViewModel by viewModel()
    private lateinit var webView: WebView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        val binding = FragmentMenuBinding.bind(view)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        startView(binding)
        startViewModel(binding)
    }

    override fun onMenuClicked(menu: Menu) {

        analytics.clickMenu(menu.option)

        when (menu.option) {
            "Indicadores" -> findNavController().navigate(R.id.action_menuFragment_to_indicatorsFragment)
            "Simulacao" -> findNavController().navigate(R.id.action_menuFragment_to_simulationFragment)
            "Tracking" -> findNavController().navigate(R.id.action_menuFragment_to_trackingFragment)
            "Comprovante" -> viewModel.dialogClick(DialogClick.OCCURRENCE_OPEN)
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

    override fun onStop() {
        super.onStop()
        viewModel.dialogClick(DialogClick.OCCURRENCE_CLOSE)
        viewModel.dialogClick(DialogClick.DELIVERED_CLOSE)
    }

    private fun startView(binding: FragmentMenuBinding) {
        binding.imageViewLogoutMenu.setOnClickListener {
            viewModel.logout()
        }
        binding.buttonDialogClose.setOnClickListener {
            viewModel.dialogClick(DialogClick.OCCURRENCE_CLOSE)
        }
        binding.buttonDialogIdentificationInvoiceClose.setOnClickListener {
            viewModel.dialogClick(DialogClick.INVOICE_CLOSE)
        }
        binding.buttonDialogDeliveredClose.setOnClickListener {
            viewModel.dialogClick(DialogClick.DELIVERED_CLOSE)
        }
        binding.buttonDialogTypeClose.setOnClickListener {
            viewModel.dialogClick(DialogClick.TYPE_CLOSE)
        }
        binding.buttonInvoiceRead.setOnClickListener {
            dispatchTakePictureIntent(DialogClick.INVOICE_OPEN)
        }
        binding.buttonCTERead.setOnClickListener {
            dispatchTakePictureIntent(DialogClick.INVOICE_OPEN)
        }
        binding.buttonDelivered.setOnClickListener {
            viewModel.dialogClick(DialogClick.INVOICE_CLOSE)
            viewModel.dialogClick(DialogClick.DELIVERED_OPEN)
        }
        binding.buttonOtherOccurrences.setOnClickListener {
            viewModel.dialogClick(DialogClick.INVOICE_CLOSE)
            viewModel.dialogClick(DialogClick.TYPE_OPEN)
        }
        binding.buttonTakePhoto.setOnClickListener {
            dispatchTakePictureIntent(DialogClick.CONFIRMED_OPEN)
            viewModel.setConfirmedMessage("Entrega comprovada!")
        }
        binding.buttonTypeConfirmed.setOnClickListener {
            viewModel.setConfirmedMessage("Ocorrência enviada!")
            viewModel.dialogClick(DialogClick.TYPE_CLOSE)
            viewModel.dialogClick(DialogClick.CONFIRMED_OPEN)
        }
        binding.buttonConfirmedOkay.setOnClickListener {
            viewModel.dialogClick(DialogClick.CONFIRMED_CLOSE)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun startViewModel(binding: FragmentMenuBinding) {
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
        viewModel.dialogOccurrence.observe(viewLifecycleOwner) {
            binding.dialogOccurrence.visibility = it
        }
        viewModel.dialogInvoice.observe(viewLifecycleOwner) {
            binding.dialogInvoice.visibility = it
        }
        viewModel.dialogDelivered.observe(viewLifecycleOwner) {
            binding.dialogDelivered.visibility = it
        }
        viewModel.dialogConfirmed.observe(viewLifecycleOwner) {
            binding.dialogConfirmed.visibility = it
        }
        viewModel.dialogType.observe(viewLifecycleOwner) {
            binding.dialogType.visibility = it
        }
        viewModel.confirmedMessage.observe(viewLifecycleOwner) {
            binding.textConfirmedMessage.text = it
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

    private val REQUEST_IMAGE_CAPTURE = 2
    private lateinit var nextDialog: DialogClick

    private fun dispatchTakePictureIntent(nextDialog: DialogClick) {
        this.nextDialog = nextDialog
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK) {
            viewModel.setPicture(data!!.extras!!.get("data") as Bitmap)
            viewModel.dialogClick(nextDialog)
        }
    }
}
