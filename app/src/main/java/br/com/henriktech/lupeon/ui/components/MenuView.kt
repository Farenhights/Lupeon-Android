package br.com.henriktech.lupeon.ui.components

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import br.com.henriktech.lupeon.R

class MenuView(context: Context?, private val option: String, private val visible: Boolean) : View(context) {

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.view_menu, null)

        val imageView = view!!.findViewById(R.id.imageMenuView) as ImageView

        val textView = view!!.findViewById(R.id.labelMenuView) as TextView
        textView.text = option

        view.visibility = getVisible(visible)
    }

    private fun getVisible(visible: Boolean): Int {
        return if(visible){
            View.VISIBLE
        } else
            View.GONE
    }
}