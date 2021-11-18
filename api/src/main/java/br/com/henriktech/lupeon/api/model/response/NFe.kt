package br.com.henriktech.lupeon.api.model.response

data class NFe(
    var CidadeDestino: String,
    var CidadeOrigem: String,
    var Comprovante: Comprovante,
    var DataEmissaoCTe: String,
    var DataEmissaoNFe: String,
    var Entregue: Boolean,
    var NFeId: Int,
    var NumeroCTe: String,
    var NumeroNFe: String,
    var Ocorrencias: List<Ocorrencia>,
    var PossuiCTe: Boolean,
    var PossuiNFe: Boolean,
    var PrazoEntrega: Int,
    var PrevisaoEntrega: String,
    var StatusEntrega: String,
    var Transportadora: String,
    var UFDestino: String,
    var UFOrigem: String
)