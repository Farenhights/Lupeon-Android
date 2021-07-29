package br.com.henriktech.lupeon.ui.profile.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.Menu


class ProfileMenuAdapter(private val mDataList: ArrayList<Menu>) :
    RecyclerView.Adapter<ProfileMenuAdapter.ProfileMenuViewHolder>() {

    private var mOnClickListener: ListItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_menu, parent, false)
        return ProfileMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileMenuViewHolder, position: Int) {
        holder.apply {
            labelMenuView.text = mDataList[position].option
            imageMenuView.setImageResource(setIcon(mDataList[position].option))
            visibility = setVisibity(mDataList[position].visible)
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    private fun setVisibity(boolean: Boolean): Int {
        return if (boolean) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    private fun setIcon(option: String): Int {
        return when (option) {
            "Indicadores" -> R.drawable.ic_indicadores
            "Simulacao" -> R.drawable.ic_simulacao
            "Tracking" -> R.drawable.ic_tracking
            "Faturas" -> R.drawable.ic_fatura
            else -> R.drawable.ic_token
        }
    }

    inner class ProfileMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val labelMenuView: TextView = itemView.findViewById(R.id.labelMenuView)
        val imageMenuView: ImageView = itemView.findViewById(R.id.imageMenuView)
        var visibility = itemView.visibility

        fun onClick(v: View?) {
            val position = adapterPosition
            mOnClickListener!!.onListItemClick(position)
        }
    }

    interface ListItemClickListener {
        fun onListItemClick(position: Int)
    }
}