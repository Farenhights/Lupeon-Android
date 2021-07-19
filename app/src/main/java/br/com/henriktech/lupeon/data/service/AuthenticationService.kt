package br.com.henriktech.lupeon.data.service

import br.com.henriktech.lupeon.api.model.LoginResponse
import br.com.henriktech.lupeon.api.network.ApiResult
import br.com.henriktech.lupeon.api.repository.SecurityRepository

class AuthenticationService(private val securityRepository: SecurityRepository) {

    suspend fun validateLogin(user: String, password: String): ApiResult<LoginResponse> {
         return securityRepository.getLogin(user, password)
    }
}