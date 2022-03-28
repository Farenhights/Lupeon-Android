package br.com.henriktech.lupeon.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.response.Ocorrencia

class OccurrenceAdapter(
    private val occurrenceList: ArrayList<Ocorrencia>,
    private val itemClickListener: OnOccurrenceClickListener,
) :
    RecyclerView.Adapter<OccurrenceAdapter.ProfileMenuViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileMenuViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_itemlist, parent, false)
        return ProfileMenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileMenuViewHolder, position: Int) {
        val occurrence = occurrenceList[position]
        holder.bind(occurrence, itemClickListener)
    }

    override fun getItemCount(): Int {
        return occurrenceList.size
    }

    inner class ProfileMenuViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val textLeftTop = itemView.findViewById<TextView>(R.id.textLeftTop)
        private val textLeftBottom = itemView.findViewById<TextView>(R.id.textLeftBottom)
        private val textRightTop = itemView.findViewById<TextView>(R.id.textRightTop)
        private val textRightBottom = itemView.findViewById<TextView>(R.id.textRightBottom)
        private val status = itemView.findViewById<ImageView>(R.id.imageStatusRoundView)
        private val item = itemView.findViewById<View>(R.id.itemList)


        fun bind(occurrence: Ocorrencia, clickListener: OnOccurrenceClickListener) {
            occurrence.run {
                textRightTop.visibility = View.GONE
                textRightBottom.visibility = View.GONE

                status.isEnabled = false

                textLeftTop.text = occurrence.DataOcorrencia
                textLeftBottom.text = occurrence.OcorrenciaLupeon

                item.setOnClickListener {
                    clickListener.onOccurrenceClicked(this)
                }
            }
        }
    }
}
