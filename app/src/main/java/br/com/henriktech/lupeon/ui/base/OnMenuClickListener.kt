package br.com.henriktech.lupeon.ui.base

import br.com.henriktech.lupeon.data.model.Menu

interface OnMenuClickListener {
    fun onMenuClicked(menu: Menu)
}