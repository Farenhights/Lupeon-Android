package br.com.henriktech.lupeon.data.service

import br.com.henriktech.lupeon.api.repository.LupeonRepository

class FilterService(private val lupeonRepository: LupeonRepository) {
    suspend fun getTransportersFilter(token: String, companyId: Int) =
        lupeonRepository.getTransportersFilter(token, companyId)

    suspend fun getPeriodsFilter(token: String, companyId: Int) =
        lupeonRepository.getPeriodsFilter(token, companyId)

    suspend fun getFilialFilter(token: String, companyId: Int) =
        lupeonRepository.getFilialFilter(token, companyId)
}