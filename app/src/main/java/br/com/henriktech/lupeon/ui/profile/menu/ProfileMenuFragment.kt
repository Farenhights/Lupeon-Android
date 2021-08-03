package br.com.henriktech.lupeon.ui.profile.menu

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.Alerta
import br.com.henriktech.lupeon.api.model.Menu
import br.com.henriktech.lupeon.ui.base.BaseFragment
import br.com.henriktech.lupeon.ui.base.IOnBackPressed
import org.koin.android.ext.android.inject


class ProfileMenuFragment : BaseFragment(R.layout.fragment_profile), OnMenuClickListener,
    IOnBackPressed, ProfileAlertAdapter.OnAlertClickListener {

    private val analytics: ProfileMenuAnalytics by inject()
    private val viewModel: ProfileMenuViewModel by inject()

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

    override fun onAlertClicked(alerta: Alerta) {
        analytics.clickAlert(alerta.titulo)
    }

    override fun onBackPressed(): Boolean {
        logoutApplication()
        return true
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
            val adapter = ProfileAlertAdapter(alerts as ArrayList<Alerta>, this)
            recycleAlert.adapter = adapter
        })

        viewModel.name.observe(viewLifecycleOwner, { name ->
            view.findViewById<TextView>(R.id.textNameProfileMenu).apply { text = "Olá, $name" }
        })

        viewModel.setLogin(getLoginActive())
    }
}
