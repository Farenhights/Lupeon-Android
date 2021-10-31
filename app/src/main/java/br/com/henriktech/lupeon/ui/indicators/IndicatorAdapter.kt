package br.com.henriktech.lupeon.ui.indicators

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.data.model.Indicator

class IndicatorAdapter(
    private val indicatorList: ArrayList<Indicator>,
    private val itemClickListener: OnIndicatorClickListener
) :
    RecyclerView.Adapter<IndicatorAdapter.IndicatorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IndicatorViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_indicator, parent, false)
        return IndicatorViewHolder(view)
    }

    override fun onBindViewHolder(holder: IndicatorViewHolder, position: Int) {
        val indicator = indicatorList[position]
        holder.bind(indicator, itemClickListener)
    }

    override fun getItemCount(): Int = indicatorList.size


    inner class IndicatorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val description = itemView.findViewById<TextView>(R.id.textIndicatorDescription)
        private val value = itemView.findViewById<TextView>(R.id.textIndicatorValue)

        fun bind(indicator: Indicator, clickListener: OnIndicatorClickListener) {
            description.text = indicator.description
            value.text = indicator.value
            itemView.setOnClickListener {
                clickListener.onIndicatorClicked(indicator)
            }
        }
    }

    interface OnIndicatorClickListener {
        fun onIndicatorClicked(indicator: Indicator)
    }
}