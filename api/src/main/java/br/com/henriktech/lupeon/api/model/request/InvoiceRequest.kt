package br.com.henriktech.lupeon.api.model.request

data class InvoiceRequest(
    val DataFim: String,
    val DataInicio: String,
    val Destinatario: Int,
    val PeriodoId: Int,
    val StatusId: Int
)