package br.com.henriktech.lupeon.ui.profile.menu

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.Menu
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject


class ProfileMenuFragment: BaseFragment( R.layout.fragment_profile), OnItemClickListener {

    private val analytics: ProfileMenuAnalytics by inject()
    private val viewModel: ProfileMenuViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        view.findViewById<ImageView>(R.id.imageViewLogoutProfileMenu).apply {
            setOnClickListener {
                logoutApplication()
            }
        }
        viewModel.menus.observe(viewLifecycleOwner, Observer {
            val recyclerView: RecyclerView = view.findViewById(R.id.recycleMenuView)
            val numberOfColumns = 2
            recyclerView.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = ProfileMenuAdapter(it as ArrayList<Menu>,this)
            recyclerView.adapter = adapter
        })

        viewModel.setLogin(getLoginActive())
    }

    override fun onItemClicked(menu: Menu) {
        when (menu.option) {
            "Indicadores" -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileIndicatorsFragment)
            "Simulacao" -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileSimulationFragment)
            "Tracking" -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileTrackingFragment)
            "Faturas" -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileInvoicesFragment)
            "Abono" -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileAllowanceFragment)
            else -> findNavController().navigate(R.id.action_profileMenuFragment_to_profileTokenFragment)
        }
    }
}