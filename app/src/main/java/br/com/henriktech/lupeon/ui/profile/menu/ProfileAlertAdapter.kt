package br.com.henriktech.lupeon.ui.profile.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.Alerta

class ProfileAlertAdapter(private val alertList: ArrayList<Alerta>, private val itemClickListener: OnAlertClickListener) :
    RecyclerView.Adapter<ProfileAlertAdapter.ProfileAlertViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAlertViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_alert, parent, false)
        return ProfileAlertViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileAlertViewHolder, position: Int) {
        val alert = alertList[position]
        holder.bind(alert, itemClickListener)
    }

    override fun getItemCount(): Int {
        return alertList.size
    }

    inner class ProfileAlertViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val alertTextView = itemView.findViewById<TextView>(R.id.alertTextView)

        fun bind(alert: Alerta, clickListener: OnAlertClickListener) {
            alertTextView.text = alert.titulo

            itemView.setOnClickListener {
                clickListener.onAlertClicked(alert)
            }
        }
    }

    interface OnAlertClickListener {
        fun onAlertClicked(alert: Alerta)
    }
}

