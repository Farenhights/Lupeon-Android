package br.com.henriktech.lupeon.data.service

import br.com.henriktech.lupeon.api.repository.EmbarcadorRepository

class IndicatorsService(private val embarcadorRepository: EmbarcadorRepository) {

    suspend fun getShipperIndicators(token: String, shipperId: Int, periodId: Int) =
        embarcadorRepository.postIndicadores(token, shipperId, periodId)
}