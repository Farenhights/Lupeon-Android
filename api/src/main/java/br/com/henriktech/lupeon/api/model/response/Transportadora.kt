package br.com.henriktech.lupeon.api.model.response

data class Transportadora(
    var NomeTabelaFrete: String,
    var NomeTransportadora: String,
    var PrazoEntrega: Int,
    var ValorFrete: Double
)