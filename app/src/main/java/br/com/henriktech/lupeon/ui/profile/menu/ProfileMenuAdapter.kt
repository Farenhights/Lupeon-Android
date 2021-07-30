package br.com.henriktech.lupeon.ui.profile.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.Menu


class ProfileMenuAdapter(private val menuList: ArrayList<Menu>, private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<ProfileMenuAdapter.ProfileMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_menu, parent, false)
        return ProfileMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileMenuViewHolder, position: Int) {
       val menu = menuList[position]
        holder.bind(menu, itemClickListener)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }


    private fun setIcon(option: String): Int {
        return when (option) {
            "Indicadores" -> R.drawable.ic_indicadores
            "Simulacao" -> R.drawable.ic_simulacao
            "Tracking" -> R.drawable.ic_tracking
            "Faturas" -> R.drawable.ic_fatura
            "Abono" -> R.drawable.ic_abono
            else -> R.drawable.ic_token
        }
    }


    inner class ProfileMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val labelMenuView: TextView = itemView.findViewById(R.id.labelMenuView)
        private val imageMenuView: ImageView = itemView.findViewById(R.id.imageMenuView)
        private var visibility = itemView.visibility

        fun bind(menu: Menu, clickListener: OnItemClickListener) {
            labelMenuView.text = menu.option
            imageMenuView.setImageResource(setIcon(menu.option))
            visibility = setVisibity(menu.visible)

            itemView.setOnClickListener {
                clickListener.onItemClicked(menu)
            }
        }

        private fun setVisibity(boolean: Boolean): Int {
            return if (boolean) {
                View.VISIBLE
            } else {
                View.GONE
            }
        }
    }
}

interface OnItemClickListener {
    fun onItemClicked(menu: Menu)
}
