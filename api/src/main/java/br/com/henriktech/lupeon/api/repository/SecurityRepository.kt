package br.com.henriktech.lupeon.api.repository

import android.content.Context
import br.com.henriktech.lupeon.api.model.LoginRequest
import br.com.henriktech.lupeon.api.network.BaseRepository

class SecurityRepository(val context: Context, private val securityApi: SecurityApi) :
    BaseRepository() {

    suspend fun getLogin(user: String, password: String) =
        safeCallApi { securityApi.getLogin(LoginRequest(user, password)) }

}