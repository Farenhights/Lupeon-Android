package br.com.henriktech.lupeon.ui.base

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.henriktech.lupeon.R
import br.com.henriktech.lupeon.api.model.response.Invoice

class InvoiceAdapter(
    private val invoiceList: ArrayList<Invoice>,
    private val itemClickListener: OnInvoiceClickListener,
) : RecyclerView.Adapter<InvoiceAdapter.InvoiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InvoiceViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_itemlist, parent, false)
        return InvoiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: InvoiceViewHolder, position: Int) {
        val invoice = invoiceList[position]
        holder.bind(invoice, itemClickListener)
    }

    override fun getItemCount(): Int = invoiceList.size

    inner class InvoiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var textLeftTop: TextView = itemView.findViewById(R.id.textLeftTop)
        private var textLeftBottom: TextView = itemView.findViewById(R.id.textLeftBottom)
        private var textRightTop: TextView = itemView.findViewById(R.id.textRightTop)
        private var textRightBottom: TextView = itemView.findViewById(R.id.textRightBottom)

        fun bind(invoice: Invoice, clickListener: OnInvoiceClickListener) {
            val custonDestiny = invoice.Destinatario.splitToSequence(" - ")
            textLeftTop = customText(custonDestiny.first(), textLeftTop)
            textLeftBottom = customText(custonDestiny.last(), textLeftBottom)
            textRightTop = customText(invoice.DataEmissaoNFe, textRightTop)
            textRightBottom = customText(invoice.NumeroNfe, textRightBottom)

            itemView.setOnClickListener {
                clickListener.onInvoiceClickListener(invoice)
            }
        }

        private fun customText(text: String?, view: TextView): TextView {
            if (text.isNullOrEmpty()) {
                view.visibility = View.GONE
            } else {
                view.visibility = View.VISIBLE
                view.text = text
            }
            return view
        }
    }
}