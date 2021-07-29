package br.com.henriktech.lupeon.ui.profile.menu

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.Menu
import br.com.henriktech.lupeon.ui.base.BaseFragment
import org.koin.android.ext.android.inject


class ProfileMenuFragment: BaseFragment( R.layout.fragment_profile) {

    private val analytics: ProfileMenuAnalytics by inject()
    private val viewModel: ProfileMenuViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        analytics.trackScreen(requireActivity())
        viewModel.setLogin(getLoginActive())

        viewModel.menus.observe(viewLifecycleOwner, Observer {
            val recyclerView: RecyclerView = view.findViewById(R.id.recycleMenuView)
            val numberOfColumns = 2
            recyclerView.layoutManager = GridLayoutManager(context, numberOfColumns)
            val adapter = ProfileMenuAdapter(it as ArrayList<Menu>)
            recyclerView.adapter = adapter
        })
    }
}