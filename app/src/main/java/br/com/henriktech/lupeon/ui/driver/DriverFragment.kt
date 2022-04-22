package br.com.henriktech.lupeon.ui.driver

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.data.model.Indicator
import br.com.henriktech.lupeon.data.model.Menu
import br.com.henriktech.lupeon.databinding.FragmentDriverMenuBinding
import br.com.henriktech.lupeon.ui.base.IOnBackPressed
import br.com.henriktech.lupeon.ui.base.IndicatorAdapter
import br.com.henriktech.lupeon.ui.base.MenuAdapter
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
        analytics.clickMenu(menu.option)
        val expression =
            if (menu.option.contains("corrência") || menu.option.contains("correncia"))
                "Ocorrencia"
            else if (menu.option.contains("Notas") || menu.option.contains("notas"))
                "Notas"
            else
                menu.option
        return when (expression) {
            "Ocorrencia" -> viewModel.dialogClick(DialogClick.OCCURRENCE_OPEN)
            "Notas" -> findNavController().navigate(R.id.action_driverFragment_to_invoiceFragment)
            else -> {}
        }
    }

    override fun onStop() {
        super.onStop()
        viewModel.dialogClick(DialogClick.OCCURRENCE_CLOSE)
        viewModel.dialogClick(DialogClick.DELIVERED_CLOSE)
    }

    private fun startView(binding: FragmentDriverMenuBinding) {
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
            viewModel.showOccurrences()
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
    private fun startViewModel(binding: FragmentDriverMenuBinding) {
        viewModel.user.observe(viewLifecycleOwner) { user ->
            if (user != null) {
                binding.textNameProfileMenu.text = "${getString(R.string.hello)}, ${user.name}"
            } else {
                findNavController().navigate(R.id.action_driverFragment_to_loginActivity)
            }
        }
        viewModel.menus.observe(viewLifecycleOwner) { menus ->
            val recycleMenu: RecyclerView = binding.recycleDriverMenuView
            val numberOfColumns = 2
            recycleMenu.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = MenuAdapter(menus, this)
            recycleMenu.adapter = adapter
        }
        viewModel.indicators.observe(viewLifecycleOwner) { indicators ->
            val recycleIndicator: RecyclerView = binding.recycleDriverIndicatorView
            val numberOfColumns = 2
            recycleIndicator.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = IndicatorAdapter(indicators as ArrayList<Indicator>)
            recycleIndicator.adapter = adapter
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
        viewModel.occurrenceList.observe(viewLifecycleOwner) {
            val filtersAdapter = ArrayAdapter(
                requireContext(),
                R.layout.spinner_text_item,
                it,
            )
            binding.spinner.apply {
                adapter = filtersAdapter
            }
        }
        viewModel.user.observe(viewLifecycleOwner) {
            val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
            with(sharedPref.edit()) {
                putString("Token", it!!.accessToken)
                putInt("CompanyId", it.companyId.toInt())
                apply()
            }
        }
        viewModel.getUser()
    }

    private val REQUEST_IMAGE_CAPTURE = 2
    private lateinit var nextDialog: DialogClick

    private fun dispatchTakePictureIntent(nextDialog: DialogClick) {
        this.nextDialog = nextDialog
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireActivity().packageManager)?.also { it ->
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
                takePictureIntent.getByteArrayExtra(it.shortClassName).let { byteArray ->
                    val imageString: String = Base64.encodeToString(byteArray!!, Base64.DEFAULT)
                    viewModel.setImageString(imageString)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            viewModel.setPicture(data!!.extras!!.get("data") as Bitmap)
            viewModel.dialogClick(nextDialog)
        }
    }
}