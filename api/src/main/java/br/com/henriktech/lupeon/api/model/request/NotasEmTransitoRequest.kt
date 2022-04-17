package br.com.henriktech.lupeon.api.model.request

data class NotasEmTransitoRequest(
    val DataFim: String,
    val DataInicio: String,
    val Destinatario: Int,
    val PeriodoId: Int,
    val StatusId: Int
)