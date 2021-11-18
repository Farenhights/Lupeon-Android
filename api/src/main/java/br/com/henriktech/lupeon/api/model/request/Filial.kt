package br.com.henriktech.lupeon.api.model.request

data class Filial(
    var CNPJFilial: Long,
    var CidadeDestino: String,
    var CidadeDestinoId: Int,
    var CidadeOrigem: String,
    var CidadeOrigemId: Int,
    var DestinatarioCNPJ: String,
    var EstadoDestino: String,
    var EstadoDestinoId: Int,
    var EstadoOrigem: String,
    var EstadoOrigemId: Int,
    var Peso: Double,
    var Produtos: List<Produto>,
    var TipoOperacao: String,
    var TipoOperacaoId: Int,
    var ValorMercadoria: Double
)