package br.com.henriktech.lupeon.data.service

import br.com.henriktech.lupeon.api.repository.LupeonRepository

class IndicatorsService(private val lupeonRepository: LupeonRepository) {

    suspend fun getShipperIndicators(token: String, shipperId: Int, companyId: Int, periodId: Int) =
        lupeonRepository.indicatorsShipper(token, shipperId, companyId, periodId)

    suspend fun getTransporterIndicators(
        token: String,
        driverId: Int,
        companyId: Int,
        periodId: Int
    ) =
        lupeonRepository.indicatorsTransporter(token, driverId, companyId, periodId)

    suspend fun getDriverIndicators(token: String, driverId: Int, companyId: Int, periodId: Int) =
        lupeonRepository.indicatorsDriver(token, driverId, companyId, periodId)

    suspend fun getTransportersFilter(token: String, companyId: Int) =
        lupeonRepository.getTransportersFilter(token, companyId)
}