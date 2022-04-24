package br.com.henriktech.lupeon.ui.base

import br.com.henriktech.lupeon.api.model.response.Invoice

interface OnInvoiceClickListener {
    fun onInvoiceClickListener(invoice: Invoice)
}