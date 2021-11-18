package br.com.henriktech.lupeon.data.service

import br.com.henriktech.lupeon.api.repository.LupeonRepository

class IndicatorsService(private val lupeonRepository: LupeonRepository) {

    suspend fun getShipperIndicators(token: String, shipperId: Int, periodId: Int) =
        lupeonRepository.indicatorsShipper(token, shipperId, periodId)
}