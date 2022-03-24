package br.com.henriktech.lupeon.data.service

import br.com.henriktech.lupeon.api.repository.LupeonRepository

class TrackingService(private val lupeonRepository: LupeonRepository) {
    suspend fun getInvoice(
        token: String,
        companyId: Int,
        invoice: Int,
        cnpj: String
    ) =
        lupeonRepository.trackingShipper(token, companyId, cnpj ,invoice)
}