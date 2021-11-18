package br.com.henriktech.lupeon.api.model.response

data class Simulacao(
    var CNPJFilial: Long,
    var CidadeDestino: String,
    var CidadeOrigem: String,
    var DestinatarioCNPJ: String,
    var DestinoUF: String,
    var MelhorFrete: Double,
    var MelhorPrazo: Int,
    var MelhorTransportadora: String,
    var Nome: String,
    var OrigemUF: String,
    var PesoConsiderado: Double,
    var Produtos: List<Produto>,
    var TipoOperacao: String,
    var TipoOperacaoId: Int,
    var Transportadoras: List<Transportadora>,
    var ValorMercadoria: Double
)