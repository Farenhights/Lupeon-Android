package br.com.henriktech.lupeon.data.service

import br.com.henriktech.lupeon.api.repository.LupeonRepository

class InvoiceService(private val lupeonRepository: LupeonRepository) {
    suspend fun getInvoice(
        token: String,
        companyId: Int,
        dataInicio: String,
        dataFim: String,
        destinatarioId: Int,
        periodoId: Int,
        status: Int
    ) = lupeonRepository.getInvoicesInTransit(
        token,
        companyId,
        dataInicio,
        dataFim,
        destinatarioId,
        periodoId,
        status
    )

    suspend fun getInvoiceDriver(
        token: String,
        companyId: Int,
        dataInicio: String,
        dataFim: String,
        destinatarioId: Int,
        periodoId: Int,
        status: Int
    ) = lupeonRepository.getInvoicesInTransitDriver(
        token,
        companyId,
        dataInicio,
        dataFim,
        destinatarioId,
        periodoId,
        status
    )
}