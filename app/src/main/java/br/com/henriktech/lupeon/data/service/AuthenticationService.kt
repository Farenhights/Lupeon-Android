package br.com.henriktech.lupeon.data.service

import br.com.henriktech.lupeon.api.repository.LupeonRepository


class AuthenticationService(private val lupeonRepository: LupeonRepository) {

    suspend fun validateLogin(user: String, password: String) =
        lupeonRepository.enterLogin(user, password)
}