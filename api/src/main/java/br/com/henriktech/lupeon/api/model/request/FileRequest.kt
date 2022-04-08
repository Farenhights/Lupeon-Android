package br.com.henriktech.lupeon.api.model.request

data class FileRequest(
    val CNPJEmpresa: String,
    val ChaveDocumento: String,
    val Comprovante: String,
    val NumeroNFe: String,
    val TipoDocumento: String
)